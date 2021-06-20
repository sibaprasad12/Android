package spm.androidworld.all.services.playMusic

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_music.*
import spm.androidworld.all.R

class MusicActivity : AppCompatActivity() {

    private var mService : MusicService? = null
    private var mBound: Boolean = false

    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            val binder = service as MusicService.MusicBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(className: ComponentName) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        buttonPause.setOnClickListener {
            mService?.playAudio()
        }

        buttonPause.setOnClickListener {
            mService?.pauseMusic()
        }

    }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        Intent(this, MusicService::class.java).also { intent ->
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }
    }
}