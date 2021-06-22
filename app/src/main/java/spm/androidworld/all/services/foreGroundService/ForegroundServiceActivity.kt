package spm.androidworld.all.services.foreGroundService

import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_foreground_service.*
import spm.androidworld.all.R


class ForegroundServiceActivity : AppCompatActivity(), View.OnClickListener {

    var count = 0

    private val myService: MyIntentService? = null
    private val isServiceBound = false
    private val serviceConnection: ServiceConnection? = null

    /*Handler handler;*/


    /*Handler handler;*/
    private var serviceIntent: Intent? = null

    private var mStopLoop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)


        Log.i(
            getString(R.string.service_demo_tag),
            "MainActivity thread id: " + Thread.currentThread().id
        )

        buttonThreadStarter.setOnClickListener(this)
        buttonStopthread.setOnClickListener(this)

        serviceIntent = Intent(applicationContext, MyIntentService::class.java)
    }
    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonThreadStarter -> {
                mStopLoop = true
                ContextCompat.startForegroundService(this, serviceIntent!!)
            }
            R.id.buttonStopthread -> stopService(serviceIntent)
        }
    }
}