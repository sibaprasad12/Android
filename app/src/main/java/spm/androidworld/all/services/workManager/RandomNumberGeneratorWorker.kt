package spm.androidworld.all.services.workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import spm.androidworld.all.R
import java.util.*


/**
 * Created by Sibaprasad Mohanty on 22/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class RandomNumberGeneratorWorker(val context: Context, val workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {


    private var mRandomNumber = 0
    private val mIsRandomGeneratorOn = false

    private val MIN = 0
    private val MAX = 100

    override fun doWork(): Result {
        startRandomNumberGenerator()
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        Log.i(context.getString(R.string.service_demo_tag),"Worker has been cancelled");
    }

    private fun startRandomNumberGenerator() {
        var i = 0
        while (i < 5 && !isStopped) {
            try {
                Thread.sleep(1000)
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = Random().nextInt(MAX) + MIN
                    Log.i(
                        context.getString(R.string.service_demo_tag),
                        "Thread id: " + Thread.currentThread().id + ", Random Number: " + mRandomNumber
                    )
                    i++
                }
            } catch (e: InterruptedException) {
                Log.i(context.getString(R.string.service_demo_tag), "Thread Interrupted")
            }
        }
    }
}