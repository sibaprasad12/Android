package spm.androidworld.all.roomDatabase.db

import androidx.room.*
import spm.androidworld.all.roomDatabase.entity.Book


/**
 * Created by Sibaprasad Mohanty on 27/06/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

@Dao
interface BookDao {

    @Insert
    fun insertAll(vararg books: Book)

    @Delete
    fun deleteBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Query("SELECT * from book")
    fun getAllBook(): List<Book>

    @Query("SELECT * FROM book WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Book>

    @Query(
        "SELECT * FROM book WHERE bookName LIKE :first AND " +
                "lastName LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Book

}