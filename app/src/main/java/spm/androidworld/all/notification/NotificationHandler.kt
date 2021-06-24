package spm.androidworld.all.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import spm.androidworld.all.R


/**
 * Created by Sibaprasad Mohanty on 24/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 *
 * https://developer.android.com/training/notify-user/build-notification#kts
 *
 */

class NotificationHandler {

    fun showNotificationForUser(context: Context, channelId: String) {
        createNotificationChannel(context)
        showNotification(
            getNotificationBuilder(
                context,
                channelId
            ), context, 1234
        )
    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =
                NotificationChannel(context.getString(R.string.channelId), name, importance).apply {
                    description = descriptionText
                }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getNotificationBuilder(
        context: Context,
        channelId: String
    ): NotificationCompat.Builder {
        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifiaction)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    private fun showNotification(
        notificationBuilder: NotificationCompat.Builder,
        context: Context,
        notificationId: Int = 1234
    ) {
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, notificationBuilder.build())
        }
    }


    /**
     * Singleton pattern implementation
     * @return
     */

    companion object {

        // Notification handler singleton
        private var nHandler: NotificationHandler? = null
        private var mNotificationManager: NotificationManager? = null

        fun getInstance(context: Context, channel_id: String): NotificationHandler? {
            if (nHandler == null) {
                nHandler = NotificationHandler()
                mNotificationManager = context.getSystemService(
                    Context.NOTIFICATION_SERVICE
                ) as NotificationManager
                // Check if the Android Version is greater than Oreo
                if (Build.VERSION.SDK_INT
                    >= Build.VERSION_CODES.O
                ) {
                    val notificationChannel = NotificationChannel(
                        channel_id, "web_app",
                        NotificationManager.IMPORTANCE_HIGH
                    )
                    mNotificationManager?.createNotificationChannel(
                        notificationChannel
                    )
                }
            }
            return nHandler
        }
    }

    /**
     * Shows a simple notification
     * @param context aplication context
     */

    fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, NotificationActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        return PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )
    }

    fun createSimpleNotification(context: Context) {
        // Pass the intent to switch to the MainActivity
        val intent = Intent(context, NotificationActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val notificationBuilder = NotificationCompat.Builder(context, channel_id)
            .setSmallIcon(R.drawable.ic_notifiaction)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentTitle("I'm a simple notification") // main title of the notification
            .setContentText("I'm the text of the simple notification") // notification text
            .setContentIntent(pendingIntent) // notification intent

        mNotificationManager?.notify(0, notificationBuilder.build())
    }


    fun createExpandableNotification(context: Context) {

        val intent = Intent(context, NotificationActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val notifBuilder: NotificationCompat.Builder

        val myBitmap = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.ic_notifiaction
        )

        // If Min. API level of the phone is 26, then notification could be
        // made asthetic
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notifChannel = NotificationChannel(
                "channelID", "description",
                NotificationManager.IMPORTANCE_HIGH
            )
            notifChannel.enableLights(true)
            notifChannel.lightColor = Color.RED
            notifChannel.enableVibration(true)

            mNotificationManager?.createNotificationChannel(notifChannel)

            notifBuilder = NotificationCompat.Builder(context, "channelID")
                .setContentTitle("Some Title")
                .setContentText("Some Content Text")
                .setSmallIcon(R.mipmap.ic_launcher_round)

                // Command to Insert Image in the Notification
                .setStyle(
                    NotificationCompat.BigPictureStyle() // <---- Look here
                        .bigPicture(myBitmap)
                ) // <---- Look here
                .setContentIntent(pendingIntent)
        }
        // Else the Android device would give out default UI attributes
        else {
            notifBuilder = NotificationCompat.Builder(context)
                .setContentTitle("Some Title")
                .setContentText("Some Content Text")
                .setContentIntent(pendingIntent)
        }

        // Everything is done now and the Manager is to be notified about
        // the Builder which built a Notification for the application
        mNotificationManager?.notify(1234, notifBuilder.build())
    }


    /**
     * Show a determinate and undeterminate progress notification
     * @param context, activity context
     */
    fun createProgressNotification(context: Context) {

    }


    fun createButtonNotification(context: Context) {
        // Pass the intent to switch to the MainActivity
        val intent = Intent(context, NotificationActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val notificationBuilder = NotificationCompat.Builder(context, channel_id)
            .setSmallIcon(R.drawable.ic_notifiaction)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .setContentTitle("I'm a simple notification") // main title of the notification
            .setContentText("I'm the text of the simple notification") // notification text
            .addAction(
                R.drawable.ic_notifiaction,
                "Accept",
                pendingIntent
            ) // accept notification button
            .addAction(
                R.drawable.ic_add_button,
                "Cancel",
                pendingIntent
            )

        mNotificationManager?.notify(0, notificationBuilder.build())
    }

    fun showNotificationBadge(context: Context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // only for gingerbread and newer version
            val id = "my_channel_01"
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_LOW
            val mChannel = NotificationChannel(id, name, importance).apply {
                description = descriptionText
                setShowBadge(false)
            }
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }


        val notification = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setContentTitle("New Messages")
            .setContentText("You've received 3 new messages.")
            .setSmallIcon(R.drawable.ic_notifiaction)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .build()

        mNotificationManager?.notify(0, notification)
    }

    fun showCountOnBadge(context: Context) {
        val notification = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setContentTitle("New Messages")
            .setContentText("You've received 3 new messages.")
            .setSmallIcon(R.drawable.ic_notifiaction)
            .setNumber(1234)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .build()

        mNotificationManager?.notify(0, notification)
    }

    fun showBadgeOnLongPress(context: Context) {

        val notification = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setContentTitle("New Messages")
            .setContentText("You've received 3 new messages.")
            .setSmallIcon(R.drawable.ic_notifiaction)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setAutoCancel(true)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setOnlyAlertOnce(true)
            .build()
        mNotificationManager?.notify(0, notification)
    }

    fun showCustomLayoutNotification(context: Context) {

        // Pass the intent to switch to the MainActivity
        val intent = Intent(context, NotificationActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        // Get the layouts to use in the custom notification
        val notificationLayout = RemoteViews(context.packageName, R.layout.notification_layout)
        val notificationLayoutExpanded =
            RemoteViews(context.packageName, R.layout.notification_large)


        val notificationBuilder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.ic_notifiaction)
            .setAutoCancel(true)
            .setOngoing(true)
            .setPriority(Notification.PRIORITY_HIGH)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setVibrate(
                longArrayOf(
                    1000, 1000, 1000,
                    1000, 1000
                )
            )
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setCustomBigContentView(notificationLayoutExpanded)

        mNotificationManager?.notify(0, notificationBuilder.build())
    }
}