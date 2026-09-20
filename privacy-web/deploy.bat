@echo off
echo ========================================================
echo Deploying Privacy Policy to Firebase Hosting
echo Project: privecy-policy-hufreshman
echo ========================================================
echo.
echo 1. Ensuring you are logged in to the correct Firebase account...
call firebase login
echo.
echo 2. Deploying website to Firebase Hosting...
call firebase deploy --only hosting
echo.
echo Done! Your Privacy Policy URL will be:
echo https://privecy-policy-hufreshman.web.app
echo ========================================================
pause
