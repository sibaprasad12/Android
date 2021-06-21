package spm.androidworld.all.services.localBindServices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class MyBindService : Service() {
    // Random number generator
    private val mGenerator = Random()
    /** method for clients  */
    val randomNumber: Int
        get() = mGenerator.nextInt(100)
    // Binder given to clients
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): MyBindService = this@MyBindService
    }
    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}