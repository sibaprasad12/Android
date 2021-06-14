package spm.architecture.designpattern

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import spm.architecture.designpattern.mvi.MviActivity
import spm.architecture.designpattern.mvp.MvpActivity
import spm.architecture.designpattern.mvvm.MvvmActivity
import spm.architecture.designpattern.mvvmWithDataBinding.MvvmDatabindingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonMvp.setOnClickListener {
            startActivity(Intent(this, MvpActivity::class.java))
        }

        buttonMvvm.setOnClickListener {
            startActivity(Intent(this, MvvmActivity::class.java))
        }

        buttonMvvmDatabinding.setOnClickListener {
            startActivity(Intent(this, MvvmDatabindingActivity::class.java))
        }

        buttonMvi.setOnClickListener {
            startActivity(Intent(this, MviActivity::class.java))
        }

    }
}