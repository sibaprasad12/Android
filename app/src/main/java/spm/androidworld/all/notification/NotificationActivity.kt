package spm.androidworld.all.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification.*
import spm.androidworld.all.R

class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        Log.i(TAG, "onCreate")

        btnSimpleNotification.setOnClickListener(this)
        btnExpandableNotification.setOnClickListener(this)
        btnProgressNotification.setOnClickListener(this)
        btnActionNotification.setOnClickListener(this)
        btnShowNotificationBadge.setOnClickListener(this)
        btnShowNotificationBadgeCount.setOnClickListener(this)
        btnLongPressNotificationBadge.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSimpleNotification -> {
                NotificationHandler.getInstance(this, "123123")?.createSimpleNotification(this)
            }
            R.id.btnExpandableNotification -> {
                NotificationHandler.getInstance(this, "123123")?.createExpandableNotification(this)
            }
            R.id.btnProgressNotification -> {
                NotificationHandler.getInstance(this, "123123")?.createProgressNotification(this)
            }
            R.id.btnActionNotification -> {
                NotificationHandler.getInstance(this, "123123")?.createButtonNotification(this)
            }
            R.id.btnShowNotificationBadge -> {
                NotificationHandler.getInstance(this, "123123")?.showNotificationBadge(this)
            }
            R.id.btnShowNotificationBadgeCount -> {
                NotificationHandler.getInstance(this, "123123")?.showCountOnBadge(this)
            }
            R.id.btnLongPressNotificationBadge -> {
                NotificationHandler.getInstance(this, "123123")?.showBadgeOnLongPress(this)
            }
            R.id.btnCustomLayoutNotification -> {
                NotificationHandler.getInstance(this, "123123")?.showCustomLayoutNotification(this)
            }
        }
    }

    companion object {
        val TAG = this.javaClass.simpleName
    }
}
