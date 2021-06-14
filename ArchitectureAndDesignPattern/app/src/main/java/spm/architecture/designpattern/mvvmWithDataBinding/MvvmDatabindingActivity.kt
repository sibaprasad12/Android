package spm.architecture.designpattern.mvvmWithDataBinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import spm.architecture.designpattern.R

class MvvmDatabindingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_databinding)
    }
}