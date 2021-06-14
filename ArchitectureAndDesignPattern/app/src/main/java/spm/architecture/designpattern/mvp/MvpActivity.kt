package spm.architecture.designpattern.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.architecture.designpattern.R

class MvpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
    }
}