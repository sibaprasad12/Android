package spm.androidworld.all.services.jobIntentService

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_job_intent_service.*
import spm.androidworld.all.R


class JobIntentServiceActivity : AppCompatActivity(), View.OnClickListener {

    var count = 0

    private val myService: MyIntentService? = null

    private var serviceIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_intent_service)

        buttonThreadStarter.setOnClickListener(this)
        buttonStopthread.setOnClickListener(this)

        Log.i(
            getString(R.string.service_demo_tag),
            "MainActivity thread id: " + Thread.currentThread().getId()
        );
        serviceIntent = Intent(applicationContext, MyIntentService::class.java)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonThreadStarter -> {
                serviceIntent!!.putExtra("starter", "starter" + ++count)
                MyIntentService.enqueueWork(this, serviceIntent)
            }
            R.id.buttonStopthread -> stopService(serviceIntent)
        }
    }

}