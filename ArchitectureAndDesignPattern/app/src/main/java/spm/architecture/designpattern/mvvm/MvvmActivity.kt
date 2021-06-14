package spm.architecture.designpattern.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import spm.architecture.designpattern.R

class MvvmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)
    }
}