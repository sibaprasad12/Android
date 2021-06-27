package spm.androidworld.all.dataStore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_data_store.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import spm.androidworld.all.R


class DataStoreActivity : AppCompatActivity(), View.OnClickListener {

    val userManager: UserManager by lazy {
        UserManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)

        buttonSaveData.setOnClickListener(this)
        buttonFetchData.setOnClickListener(this)
        buttonSaveProto.setOnClickListener(this)
        buttonFetchProto.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.buttonSaveData -> {
                CoroutineScope(IO).launch {
                    userManager.storeUser(
                        this@DataStoreActivity,
                        etFirstName.text.toString(),
                        etAge.text.toString().toInt(),
                        etisMarried.text.toString().toBoolean()
                    )
                }
            }
            R.id.buttonFetchData -> {

                CoroutineScope(IO).launch {

                    with(userManager) {
                        fetchUserName(this@DataStoreActivity).collect {
                            etFirstName.setText(it)
                        }
                        fetchUserAge(this@DataStoreActivity).collect {
                            etAge.setText(it.toString())
                        }
                        fetchUserMarried(this@DataStoreActivity).collect {
                            etisMarried.setText("$it")
                        }
                    }
                }
            }
            R.id.buttonSaveProto -> {
                etFirstNameProto.text
                etAgeProto.text
                etisMarriedProto.text
            }
            R.id.buttonFetchProto -> {
                etFirstNameProto.text
                etAgeProto.text
                etisMarriedProto.text
            }
        }
    }

// Write to proto
/*
suspend fun incrementCounter() {
context.settingsDataStore.updateData { currentSettings ->
currentSettings.toBuilder()
  .setExampleCounter(currentSettings.exampleCounter + 1)
  .build()
}
}
 */
}