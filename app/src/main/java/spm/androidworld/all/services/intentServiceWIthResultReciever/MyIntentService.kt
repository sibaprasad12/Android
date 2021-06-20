package spm.androidworld.all.services.intentServiceWIthResultReciever

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver


class MyIntentService : IntentService("MyIntentService") {

    val STATUS_RUNNING = 0
    val STATUS_FINISHED = 1
    val STATUS_ERROR = 2

    override fun onHandleIntent(intent: Intent?) {
        val receiver: ResultReceiver = intent!!.getParcelableExtra("receiver")


        //TODO: process background task here!

        /*
         * Step 2: Now background service is processed,
         * we can pass the status of the service back to the activity using the resultReceiver
         *  */


        //TODO: process background task here!

        /*
         * Step 2: Now background service is processed,
         * we can pass the status of the service back to the activity using the resultReceiver
         *  */
        val b = Bundle()
        receiver.send(STATUS_FINISHED, b)
    }
}