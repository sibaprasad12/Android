package spm.androidworld.all.services.jobService

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent


class MyJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        val service = Intent(applicationContext, MyJobService::class.java)
        applicationContext.startService(service)
        Util.scheduleJob(applicationContext) // reschedule the job
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }


}