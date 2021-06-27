package spm.androidworld.all.roomDatabase.db

import androidx.room.*
import spm.androidworld.all.roomDatabase.entity.User


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Dao
interface UserDao {

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("SELECT * from user")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query(
        "SELECT * FROM user WHERE firstName LIKE :first AND " +
                "lastName LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): User

}