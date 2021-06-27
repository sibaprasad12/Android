package spm.androidworld.all.dataStore


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/*val Context.settingsDataStore: DataStore<Settings> by dataStore(
    fileName = "settings.pb",
    serializer = SettingsSerializer
)*/

class UserManager(context: Context) {

    // Create some keys we will use them to store and retrieve the data
    companion object {
        val USER_NAME_KEY = stringPreferencesKey("userName")
        val USER_AGE_KEY = intPreferencesKey("userAge")
        val USER_ISMARRIED_KEY = booleanPreferencesKey("isMarried")
    }

    // Store user data
    // refer to the data store and using edit
    // we can store values using the keys
    suspend fun storeUser(context: Context, name: String, age: Int, isMarried: Boolean) {
        context.dataStore.edit { it ->
            it[USER_NAME_KEY] = name
            it[USER_AGE_KEY] = age
            it[USER_ISMARRIED_KEY] = isMarried
            // here it refers to the preferences we are editing
        }
    }

    suspend fun fetchUserName(context: Context): Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_NAME_KEY] ?: ""
        }

    suspend fun fetchUserAge(context: Context): Flow<Int> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_AGE_KEY] ?: 0
        }

    suspend fun fetchUserMarried(context: Context): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_ISMARRIED_KEY] ?: false
        }
}


