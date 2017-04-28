# Android Notifications Sample

This is a sample to help developers handling notifications on Android.

You can set a daily or weekly notification rate, or you can turn them off.

Notifications work even after reboot or removing the app from RAM.

Also, in this project we'll use a TimePicker to set your time.

MinSDK: 21

If you like the project, star it. :)

# Screenshots
<img src="https://github.com/Cesarsk/Android-Notifications-Sample/blob/master/screenshots/screen1.png" width="40%" /> <img src="https://github.com/Cesarsk/Android-Notifications-Sample/blob/master/screenshots/screen2.png" width="40%" />

# License
Licensed under Apache License, see License.md for further details.

# F.A.Q.

**Why the notification don't appear / won't instantly appear if the app has been killed?**

*setInexactRepeating* is not accurate if the app has been killed.
Be patient and wait until it launches. If you need an accurate notification, search for a replacement of setInexactRepeating on the official Android SDK;


To test your app, try to build it pressing the PLAY button on Android Studio. Then press STOP and then try to launch the app manually from your app drawer, and test your notifications.


Finally, your device may be the problem. If you have a Huawei (probably other brands too) you need to add apps in Protected Apps in order to receive notifications by them.

If that's not your case, you can try to factory-reset your phone.
