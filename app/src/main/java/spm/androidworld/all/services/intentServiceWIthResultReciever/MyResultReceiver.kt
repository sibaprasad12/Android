package spm.androidworld.all.services.intentServiceWIthResultReciever

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver


/**
 * Created by Sibaprasad Mohanty on 20/6/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

class MyResultReceiver(handler: Handler, var appReceiver: AppReceiver?) : ResultReceiver(handler) {

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        super.onReceiveResult(resultCode, resultData)
        appReceiver?.onReceiveResult(resultCode, resultData)
    }

    interface AppReceiver {
        fun onReceiveResult(resultCode: Int, resultData: Bundle?)
    }
}