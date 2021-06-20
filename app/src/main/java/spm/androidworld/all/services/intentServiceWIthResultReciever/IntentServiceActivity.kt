package spm.androidworld.all.services.intentServiceWIthResultReciever

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import spm.androidworld.all.R


class IntentServiceActivity : AppCompatActivity(), MyResultReceiver.AppReceiver {

    lateinit var myResultReceiver: MyResultReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)
    }

    /*
     * Step 1: Register the intent service in the activity
     * */
    private fun registerService() {
        val intent = Intent(applicationContext, MyIntentService::class.java)

        /*
         * Step 2: We pass the ResultReceiver via the intent to the intent service
         * */
        myResultReceiver = MyResultReceiver(Handler(), this)
        intent.putExtra("receiver", myResultReceiver)
        startService(intent)
    }


    override
    fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        /*
         * Step 3: Handle the results from the intent service here!
         * */
    }


    override fun onStop() {
        super.onStop()

        /*
         * Step 4: don't forget to clear receiver in order to avoid leaks.
         * */if (myResultReceiver != null) {
            myResultReceiver.appReceiver = null
        }
    }

}