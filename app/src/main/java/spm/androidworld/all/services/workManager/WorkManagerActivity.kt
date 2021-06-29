package spm.androidworld.all.services.workManager

import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import kotlinx.android.synthetic.main.activity_work_manager.*
import spm.androidworld.all.R
import java.util.concurrent.TimeUnit


class WorkManagerActivity : AppCompatActivity(), View.OnClickListener {

    var count = 0

    private val isServiceBound = false
    private val serviceConnection: ServiceConnection? = null

    var workManager: WorkManager? = null

    /*Handler handler;*/


    /*Handler handler;*/
    private val serviceIntent: Intent? = null

    private var mStopLoop = false
    private var workRequest: WorkRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)


        buttonThreadStarter.setOnClickListener(this)
        buttonStopthread.setOnClickListener(this)

        workManager = WorkManager.getInstance(applicationContext)

        //workRequest = OneTimeWorkRequest.from(RandomNumberGeneratorWorker.class)

        workRequest =  PeriodicWorkRequest.Builder(RandomNumberGeneratorWorker::class.java, 15, TimeUnit.MINUTES).build()


    }

    override fun onClick(view: View) {
        when (view.getId()) {
            R.id.buttonThreadStarter -> {
                mStopLoop = true
                workManager!!.enqueue(workRequest!!)

                /*WorkManager.getInstance(...)
                .beginWith(listOf(workA,workB))
                    .then(workC)
                    .enqueue()*/

            }
            R.id.buttonStopthread -> workManager!!.cancelWorkById(workRequest!!.id)
        }
    }
}