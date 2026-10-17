package com.curiovana.hufreshman

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * FCM service for HU Freshman.
 *
 * - On first launch: subscribes to the "admin_posts" topic so every device
 *   automatically receives notifications when the admin publishes a new post.
 * - When a notification arrives while the app is in the foreground, this service
 *   builds and shows the system notification manually (FCM only auto-shows it
 *   when the app is in the background).
 */
class HuFreshmanMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Re-subscribe whenever the FCM token rotates
        subscribeToAdminTopic()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title
            ?: message.data["title"]
            ?: "HU Freshman"
        val body = message.notification?.body
            ?: message.data["body"]
            ?: return   // nothing to show

        val channelId = if (message.data["type"] == "admin_verification") ADMIN_CHANNEL_ID else CHANNEL_ID
        showNotification(title, body, channelId)
    }

    private fun showNotification(title: String, body: String, channelId: String = CHANNEL_ID) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create the channels (no-op on API < 26 or if already exists)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val postChannel = NotificationChannel(
                CHANNEL_ID,
                "Admin Announcements",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for new posts from HU Freshman admin"
            }
            val adminChannel = NotificationChannel(
                ADMIN_CHANNEL_ID,
                "Admin Verifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications for admin to accept or reject member payment verifications"
            }
            manager.createNotificationChannel(postChannel)
            manager.createNotificationChannel(adminChannel)
        }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            if (channelId == ADMIN_CHANNEL_ID) {
                putExtra("navigate_to", "ADMIN")
            }
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            System.currentTimeMillis().toInt(),
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }

    companion object {
        const val CHANNEL_ID = "hu_freshman_admin_posts"
        const val ADMIN_CHANNEL_ID = "hu_freshman_admin_verifications"

        /** Call once at app startup to ensure every device is subscribed. */
        fun subscribeToAdminTopic() {
            FirebaseMessaging.getInstance()
                .subscribeToTopic("admin_posts")
                // Silent — no UI feedback needed; FCM retries automatically if offline
        }

        /** Subscribes or unsubscribes the admin device to receive screenshot verification alerts */
        fun syncAdminSubscription(isAdmin: Boolean) {
            if (isAdmin) {
                FirebaseMessaging.getInstance().subscribeToTopic("admin_notifications")
            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("admin_notifications")
            }
        }
    }
}
