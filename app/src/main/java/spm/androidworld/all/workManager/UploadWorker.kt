package spm.androidworld.all.workManager

import android.content.Context
import androidx.work.WorkRequest
import androidx.work.Worker

class UploadWorker(appContext: Context, workerParams: WorkRequest?):
       Worker(appContext, workerParams) {
   override fun doWork(): Result {

       // Do the work here--in this case, upload the images.
      // uploadImages()

       // Indicate whether the work finished successfully with the Result
       return Result.success()
   }
}