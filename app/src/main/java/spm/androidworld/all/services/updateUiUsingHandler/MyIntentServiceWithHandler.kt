package spm.androidworld.all.services.updateUiUsingHandler

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Message

class MyIntentServiceWithHandler : IntentService("MyIntentServiceWithHandler") {

    val STATUS_RUNNING = 0
    val STATUS_FINISHED = 1
    val STATUS_ERROR = 2

    override fun onHandleIntent(intent: Intent?) {
/*
         * Step 1: We pass the Handler from the activity to the intent service via intent.
         *  */
        /*
         * Step 1: We pass the Handler from the activity to the intent service via intent.
         *  */
        val handler: Handler? = intent?.getParcelableExtra("handler")

        //TODO: process background task here!

        /*
         * Step 2: Now background service is processed,
         * we can pass the status of the service back to the activity using the handler
         *  */

        //TODO: process background task here!

        /*
         * Step 2: Now background service is processed,
         * we can pass the status of the service back to the activity using the handler
         *  */
        val msg = Message()
        msg.obj = "Sending message to UI after completion of background task!"
        msg.what = STATUS_FINISHED
        handler?.sendMessage(msg)
    }
}