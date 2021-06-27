package spm.androidworld.all.workManager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import spm.androidworld.all.R
import java.util.concurrent.TimeUnit


class WorkManagerActivity : AppCompatActivity() {

    var count = 0
    var workManager: WorkManager? = null

    private var mStopLoop = false
    private var workRequest: WorkRequest? = null
    private var workRequestNumber: WorkRequest? = null

    val uploadDataConstraints =
        Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    val oneTimeWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>()
        .setConstraints(uploadDataConstraints)
        .build()

    var workParameter: WorkerParameters? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager2)

        workManager = WorkManager.getInstance(applicationContext)
//        workParameter = WorkerParameters(123)
//        workRequestNumber = OneTimeWorkRequest.from(RandomNumberGeneratorWorker.class)

        workRequestNumber = PeriodicWorkRequest.Builder(
            RandomNumberGeneratorWorker::class.java,
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this)
            .enqueue(workRequestNumber as PeriodicWorkRequest)

        /*
        // Start Periodic work
        WorkManager.getInstance(this)
            .beginWith(
                listOf(
                    UploadWorker(this, oneTimeWorkRequest),
                    UploadWorker1(this, oneTimeWorkRequest)
                )
            )
            .then(RandomNumberGeneratorWorker(this, workRequestNumber))
            .enqueue()
        */

        /*
        WorkManager.getInstance(myContext)
            // First, run all the A tasks (in parallel):
            .beginWith(Arrays.asList(workA1, workA2, workA3))
            // ...when all A tasks are finished, run the single B task:
            .then(workB)
            // ...then run the C tasks (in parallel):
            .then(Arrays.asList(workC1, workC2))
            .enqueue()
         */

    }
}