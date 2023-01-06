package com.andriamparivonyschubert.henripotierapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andriamparivonyschubert.henripotierapp.data.BookRepo

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val allBooks: LiveData<List<Book>>

    init {
        repository = BookRepo(application)
        allBooks = repository.getAllBooks()
    }

    companion object {
        lateinit var repository: BookRepo
        fun insertAll(books: List<Book>) {
            repository.insertAll(books)
        }

        fun get(isbn: String): LiveData<Book> {
            return repository.get(isbn)
        }
    }
}