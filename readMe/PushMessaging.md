# Push Notificatio

## FCM - Firebase Cloud Messaging
- Firebase Cloud Messaging (FCM) is a set of tools that sends push notifications and small messages of up to 4 KB to different platforms: Android, iOS and web.
- We are going to discuss
    - How to set up Firebase and create a project in it.
    - A method to connect your mobile app with the Firebase console.
    - The difference between receiving messages in the foreground and background of your app.
    - How to use the data received in the messages.
    - How to send test messages through Firebase console.

## What Is Firebase Cloud Messaging?
- Push notifications — those small alerts that slide in from the top of our screen, letting us know an app needs our attention — have been around since the early days of Android apps. There are many tools that can help us add this functionality, but Firebase Cloud Messaging is one of the easiest and most straightforward to add in your projects.

## These are the required architecture in FCM
- A service, API or console that sends messages to targeted devices.
- The Firebase Cloud Messaging back end, where all the processing happens.
- A transport layer that’s specific to each platform. In Android’s case, this is called the Android Transport Layer.
- The SDK on the device where you’ll receive the messages. In this case, called the Android Firebase Cloud Messaging SDK.

## Configure the Project
- Go to https://console.firebase.google.com/u/0/?pli=1
- Create a project by selecting the desired platform (Android, IOS, Web)
- Now Register your app with FCM by entering the details (AppName, Package Name, SH1 key)
- Download the google_service.json file and add to the root of your android application directory(Inside app folder)
- add in classpath 'com.google.gms:google-services:4.3.3' build.gradle
```
// Add the following line:
    classpath 'com.google.gms:google-services:4.3.8'  // Google Services plugin
```
- add to your app level gradle file
```
    apply plugin: 'com.google.gms.google-services'

    // Declare the dependencies for the Firebase Cloud Messaging and Analytics libraries
        // When using the BoM, you don't specify versions in Firebase library dependencies
        implementation 'com.google.firebase:firebase-messaging'
        implementation 'com.google.firebase:firebase-analytics'

```

- Add this below code in manifest file
```
    <service
        android:name=".java.MyFirebaseMessagingService"
        android:exported="false">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT" />
        </intent-filter>
    </service>
```
- This is optional
```
    <!-- Set custom default icon. This is used when no icon is set for incoming notification messages.
         See README(https://goo.gl/l4GJaQ) for more. -->
    <meta-data
        android:name="com.google.firebase.messaging.default_notification_icon"
        android:resource="@drawable/ic_stat_ic_notification" />
    <!-- Set color used with incoming notification messages. This is used when no color is set for the incoming
         notification message. See README(https://goo.gl/6BKBk7) for more. -->
    <meta-data
        android:name="com.google.firebase.messaging.default_notification_color"
        android:resource="@color/colorAccent" />
```
- For android 8 and above
```
    <meta-data
        android:name="com.google.firebase.messaging.default_notification_channel_id"
        android:value="@string/default_notification_channel_id" />
```

- Create a class by extending FirebaseMessagingService and override the methods
```
    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
            Log.w(TAG, "Fetching FCM registration token failed", task.exception)
            return@OnCompleteListener
        }

        // Get new FCM registration token
        val token = task.result

        // Log and toast
        val msg = getString(R.string.msg_token_fmt, token)
        Log.d(TAG, msg)
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    })
```
- On new token generated
```
    /**
     * Called if the FCM registration token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the
     * FCM registration token is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token)
    }
```

## Prevent auto initialization
- When an FCM registration token is generated, the library uploads the identifier and configuration data to Firebase.
- If you prefer to prevent token autogeneration, disable Analytics collection and FCM auto initialization (you must disable both) by adding these metadata values to your AndroidManifest.xml:
```
<meta-data
        android:name="firebase_messaging_auto_init_enabled"
        android:value="false" />
    <meta-data
        android:name="firebase_analytics_collection_enabled"
        android:value="false" />
```
## Type of Push Notification
- Topic Notification
- Group Device Notification
- upstream messages

## https://stackoverflow.com/questions/56619598/want-to-know-how-fcm-works-in-below-scenarios


## Is it possible to receive FCM push notification when app is killed?
- There are 2 types of push notifications:
    - Data messages
    - Notification messages
- If you are using the Data messages you will be in charge of handling the received message and present a notification to the user (if needed of course). But in this case you might miss notifications when your app is closed.
- If you are using Notification Messages, FCM is handling the message for you and directly displays a notification if the app is in background/closed.

## Data Messages
- You should use data messages if you need to handle the notification in the client app, whether to customize the notification or to decrypt the received payload data.
- A data message is normal priority by default, which means it will be batched to the next maintenance window when the device is in Doze, by default.

>> Add https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo/related
to your chrome to send notification
- Select POST method
- enter this url https://fcm.googleapis.com/fcm/send
- header
    - contenttype : application/json
    - authorisation : key = frebase serverKey
- Body
    {
      "data": {
       "title": "Portugal vs. Denmark",
          "content": "great match!"
      },
      // ur device token
      "to":"fNMyIRF1SvS9rvwB5gWsd0:APA91bEuBfvEzUE4yqxJtNicuu0KBOzmAEhNT-U2i1inhan4M0TocyuwgWDEHOoKJt42SF8qS8ZX-8TRSuvyYra2gkpwR6maCUcI3HJGKklm4Ztpb6p51CRP4frbF-HDKWW3XTWy9jiP"
    }

>> If your application in foreground, you will receive message in onReceive method of FirebaseMessagingService
 else you will get the notification data in the Launcher activity, You have to access the data like this

```
/**
         * To Fetch the notification
         */
        val intent = intent
        var notificationMessage = ""
        intent.extras?.keySet()?.forEach { key ->
            if (key == "title") {
                notificationMessage += "Title = ${intent.extras?.getString(key)}"
            }
            if (key == "message") {
                notificationMessage += "Message = ${intent.extras?.getString(key)}"
            }
        }
```