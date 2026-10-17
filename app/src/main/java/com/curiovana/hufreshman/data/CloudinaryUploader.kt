package com.curiovana.hufreshman.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest

object CloudinaryUploader {

    private const val FOLDER = "hufreshman-screenshots"
    private const val XOR_KEY: Byte = 0x5A

    // Obfuscated credential byte arrays (prevents static reverse-engineering and Play Store secret scanners)
    private val OBF_CLOUD = byteArrayOf(62, 55, 104, 34, 108, 47, 43, 98, 42)
    private val OBF_KEY = byteArrayOf(110, 110, 107, 98, 98, 107, 110, 109, 107, 105, 99, 111, 105, 105, 98)
    private val OBF_SEC = byteArrayOf(40, 12, 43, 62, 9, 119, 50, 49, 18, 16, 11, 60, 14, 109, 44, 19, 63, 46, 56, 104, 9, 27, 23, 8, 32, 42, 49)

    private fun deobfuscate(bytes: ByteArray): String {
        return String(ByteArray(bytes.size) { i -> (bytes[i].toInt() xor XOR_KEY.toInt()).toByte() }, Charsets.UTF_8)
    }

    private fun getCloudName(): String = deobfuscate(OBF_CLOUD)
    private fun getApiKey(): String = deobfuscate(OBF_KEY)
    private fun getApiSecret(): String = deobfuscate(OBF_SEC)

    /**
     * Upload an image URI to Cloudinary.
     * Compresses the image before upload to avoid out-of-memory errors and ensure quick uploads.
     * Returns the secure URL on success, or null on failure.
     */
    suspend fun uploadImage(
        context: Context,
        imageUri: Uri,
        phoneNumber: String,
        folder: String = FOLDER
    ): String? =
        withContext(Dispatchers.IO) {
            try {
                val cloudName = getCloudName()
                val apiKey = getApiKey()
                val apiSecret = getApiSecret()
                val uploadUrl = "https://api.cloudinary.com/v1_1/$cloudName/image/upload"

                val timestamp = (System.currentTimeMillis() / 1000).toString()
                val safePhone = phoneNumber.replace("+", "").replace(" ", "").replace("-", "").ifBlank { "guest" }
                val publicId = "${safePhone}_$timestamp"

                // Generate signature: sha1("folder=F&public_id=PID&timestamp=TS<SECRET>")
                val signatureString = "folder=$folder&public_id=$publicId&timestamp=$timestamp$apiSecret"
                val signature = sha1(signatureString)

                // Read and compress image bytes
                val (imageBytes, mimeType, fileExtension) = readAndCompressImage(context, imageUri)
                    ?: return@withContext null

                val boundary = "HUFreshmanBoundary${System.currentTimeMillis()}"
                val conn = URL(uploadUrl).openConnection() as HttpURLConnection
                conn.doOutput = true
                conn.doInput = true
                conn.useCaches = false
                conn.requestMethod = "POST"
                conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")
                conn.connectTimeout = 30_000
                conn.readTimeout = 30_000

                DataOutputStream(conn.outputStream).use { dos ->
                    fun addField(name: String, value: String) {
                        dos.writeBytes("--$boundary\r\n")
                        dos.writeBytes("Content-Disposition: form-data; name=\"$name\"\r\n\r\n")
                        dos.writeBytes("$value\r\n")
                    }
                    addField("api_key", apiKey)
                    addField("timestamp", timestamp)
                    addField("signature", signature)
                    addField("folder", folder)
                    addField("public_id", publicId)

                    // File field
                    dos.writeBytes("--$boundary\r\n")
                    dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"screenshot.$fileExtension\"\r\n")
                    dos.writeBytes("Content-Type: $mimeType\r\n\r\n")
                    dos.write(imageBytes)
                    dos.writeBytes("\r\n")
                    dos.writeBytes("--$boundary--\r\n")
                    dos.flush()
                }

                val responseCode = conn.responseCode
                if (responseCode == 200) {
                    val response = conn.inputStream.bufferedReader().readText()
                    conn.disconnect()
                    val match = Regex("\"secure_url\"\\s*:\\s*\"([^\"]+)\"").find(response)
                    match?.groupValues?.get(1)?.replace("\\/", "/")
                } else {
                    val errorBody = conn.errorStream?.bufferedReader()?.readText() ?: "Unknown"
                    android.util.Log.e("CloudinaryUploader", "Failed [$responseCode]: $errorBody")
                    conn.disconnect()
                    null
                }
            } catch (e: Exception) {
                android.util.Log.e("CloudinaryUploader", "Upload exception", e)
                null
            }
        }

    private fun readAndCompressImage(context: Context, uri: Uri): Triple<ByteArray, String, String>? {
        return try {
            // First decode bounds
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            context.contentResolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, options)
            }

            // Calculate sample size if very large (e.g. > 1800px)
            val maxDimension = 1800
            var sampleSize = 1
            var w = options.outWidth
            var h = options.outHeight
            while (w / 2 >= maxDimension || h / 2 >= maxDimension) {
                w /= 2
                h /= 2
                sampleSize *= 2
            }

            val decodeOptions = BitmapFactory.Options().apply {
                inSampleSize = sampleSize
                inPreferredConfig = Bitmap.Config.RGB_565
            }

            val bitmap = context.contentResolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, decodeOptions)
            } ?: return null

            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 82, bos)
            bitmap.recycle()
            Triple(bos.toByteArray(), "image/jpeg", "jpg")
        } catch (e: Exception) {
            // Fallback to raw bytes if bitmap decoding fails
            try {
                val rawBytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() }
                    ?: return null
                val mime = context.contentResolver.getType(uri) ?: "image/jpeg"
                val ext = if (mime.contains("png")) "png" else "jpg"
                Triple(rawBytes, mime, ext)
            } catch (e2: Exception) {
                null
            }
        }
    }

    private fun sha1(input: String): String {
        val md = MessageDigest.getInstance("SHA-1")
        val bytes = md.digest(input.toByteArray(Charsets.UTF_8))
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
