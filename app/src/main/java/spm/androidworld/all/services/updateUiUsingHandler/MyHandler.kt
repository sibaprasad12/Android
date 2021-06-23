package spm.androidworld.all.services.updateUiUsingHandler

import android.os.Handler
import android.os.Message


/**
 * Created by Sibaprasad Mohanty on 20/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MyHandler(receiver: AppReceiver) : Handler() {

    private val appReceiver: AppReceiver = receiver

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        appReceiver.onReceiveResult(msg)
    }

    interface AppReceiver {
        fun onReceiveResult(message: Message?)
    }

}