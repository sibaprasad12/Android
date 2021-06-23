package spm.androidworld.all.fcm

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_push_notification.*
import spm.androidworld.all.R


class PushNotificationActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "PushNotificationActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push_notification)

        buttonToken.setOnClickListener(this)
        buttonRegisterToTopic_news.setOnClickListener(this)
        buttonRegisterToTopic_technology.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        checkPlayServices()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonToken -> {
                getDeviceTokenFromFcm()
            }
            R.id.buttonRegisterToTopic_news -> {
                subscribeToTopic(getString(R.string.fcm_topic_news))
            }
            R.id.buttonRegisterToTopic_technology -> {
                subscribeToTopic(getString(R.string.fcm_topic_technology))
            }
        }
    }

    fun getDeviceTokenFromFcm() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->

            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
            }

            // Get new FCM registration token

            // Get new FCM registration token
            val token: String? = task.result

            // Log and toast

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)
            Toast.makeText(this@PushNotificationActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    fun subscribeToTopic(topic: String) {

        FirebaseMessaging.getInstance().subscribeToTopic(topic).addOnCompleteListener {

            var msg = if (it.isSuccessful) {
                "Successfully Subscribe to Topic $topic"
            } else {
                getString(R.string.msg_subscribe_failed)
            }
            Log.d(TAG, msg)
            Toast.makeText(this@PushNotificationActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPlayServices(): Boolean {
        val gApi: GoogleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode: Int = gApi.isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (gApi.isUserResolvableError(resultCode)) {
                gApi.getErrorDialog(this, resultCode, 1234).show()
            } else {
                Toast.makeText(
                    this,
                    resources.getString(R.string.toast_playservices_unrecoverable),
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
            return false
        }
        return true
    }


}