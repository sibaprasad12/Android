package spm.androidworld.all.roomDatabase.db

import android.content.Context
import androidx.room.Room


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */


class DatabaseClient(mCtx: Context) {
    private var mInstance: DatabaseClient? = null

    //our app database object
    private var appDatabase: AppDatabase? = null

    init {
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase::class.java, "MyToDos").build()
    }

    @Synchronized
    fun getInstance(mCtx: Context): DatabaseClient? {
        if (mInstance == null) {
            mInstance = DatabaseClient(mCtx)
        }
        return mInstance
    }

    fun getAppDatabase(): AppDatabase? {
        return appDatabase
    }
}