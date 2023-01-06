package com.andriamparivonyschubert.henripotierapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andriamparivonyschubert.henripotierapp.model.Book

@Dao
interface BookDao {
    @Query("DELETE FROM book")
    fun deleteAll()

    @Query("SELECT * FROM book")
    fun getAll(): LiveData<List<Book>>

    @Query("SELECT * FROM book WHERE isbn = :isbn")
    fun getByISBN(isbn: String): LiveData<Book>

    @Insert
    fun insertAll(books: List<Book>)

    @Insert
    fun insertBook( book: Book)
}