package spm.androidworld.all.roomDatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val author: String
)