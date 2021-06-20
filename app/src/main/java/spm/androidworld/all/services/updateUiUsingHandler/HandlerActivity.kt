package spm.androidworld.all.services.updateUiUsingHandler

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.os.Messenger
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R


class HandlerActivity : AppCompatActivity(), MyHandler.AppReceiver {

    lateinit var handler: MyHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
    }

    /*
     * Step 1: Register the intent service in the activity
     * */
    private fun registerService() {
        val intent = Intent(applicationContext, MyIntentServiceWithHandler::class.java)

        /*
         * Step 2: We pass the handler via the intent to the intent service
         * */
        handler = MyHandler(this)
        intent.putExtra("handler", Messenger(handler))
        startService(intent)
    }


    override fun onReceiveResult(message: Message?) {
        /*
         * Step 3: Handle the results from the intent service here!
         * */
        when (message?.what) {

        }
    }
}