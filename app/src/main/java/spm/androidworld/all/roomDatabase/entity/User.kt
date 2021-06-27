package spm.androidworld.all.roomDatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "firstName") val fName: String,
    @ColumnInfo(name = "lastName") val lName: String,
    @ColumnInfo(name = "userAge") val age: Int,
    @ColumnInfo(name = "userAddress") val address: String
)