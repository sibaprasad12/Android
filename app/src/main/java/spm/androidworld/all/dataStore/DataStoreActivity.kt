package spm.androidworld.all.dataStore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_data_store.*
import spm.androidworld.all.R


class DataStoreActivity : AppCompatActivity(), View.OnClickListener {


// At the top level of your kotlin file:
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


    /*
    val exampleCounterFlow: Flow<Int> = context.settingsDataStore.data
        .map { settings ->
            // The exampleCounter property is generated from the proto schema.
            settings.exampleCounter
        }
    */
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
            /*R.id.buttonSaveData -> {
                etFirstName.text
                etAge.text
                etisMarried.text
            }
            R.id.buttonFetchData -> {
                etFirstName.text
                etAge.text
                etisMarried.text
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
            }*/
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