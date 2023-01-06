package com.andriamparivonyschubert.henripotierapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.andriamparivonyschubert.henripotierapp.model.Book
import com.andriamparivonyschubert.henripotierapp.util.AppDatabase

class BookRepo(application: Application) {
    private val bookDao: BookDao
    private val allBooks: LiveData<List<Book>>

    init {
        val database: AppDatabase = AppDatabase.getInstance(application)
        bookDao = database.bookDao()
        allBooks = bookDao.getAll()
    }

    fun getAllBooks(): LiveData<List<Book>> {
        return allBooks
    }

    /*fun insert(book: Book?) {
        AppDatabase.databaseWriterExecutor.execute {
            BookDao.insertBook(book)
        }
    }*/

    operator fun get(isbn: String): LiveData<Book> {
        return bookDao.getByISBN(isbn)
    }

    fun insertAll(books: List<Book>) {
        AppDatabase.databaseWriterExecutor.execute { bookDao.insertAll(books) }
    }
}