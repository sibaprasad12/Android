package spm.androidworld.all.workManager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */
class UploadWorker1(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        // Do the work here--in this case, upload the images.
        // uploadImages()

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }
}