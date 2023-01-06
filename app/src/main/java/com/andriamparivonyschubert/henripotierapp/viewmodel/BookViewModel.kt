package com.andriamparivonyschubert.henripotierapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andriamparivonyschubert.henripotierapp.data.BookRepo
import com.andriamparivonyschubert.henripotierapp.data.HenriPotierApi
import com.andriamparivonyschubert.henripotierapp.model.Book
import com.andriamparivonyschubert.henripotierapp.util.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(application: Application) : AndroidViewModel(application) {
    val state = MutableLiveData<LibraryState>()
    private val allBooks: LiveData<List<Book>>

    init {
        repository = BookRepo(application)
        allBooks = repository.getAllBooks()
    }

    fun loadBooks() {

        val service: HenriPotierApi = RetrofitHelper.getInstance().create(HenriPotierApi::class.java)
        
        state.postValue(LibraryState(emptyList(), true))

        viewModelScope.launch(context = Dispatchers.Main) {
            val books = withContext(Dispatchers.IO) {
                service.getBooks()
            }
            state.postValue(LibraryState(books, false))
        }
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