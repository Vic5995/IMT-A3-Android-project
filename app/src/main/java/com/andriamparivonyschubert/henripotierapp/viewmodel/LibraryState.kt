package com.andriamparivonyschubert.henripotierapp.viewmodel

import com.andriamparivonyschubert.henripotierapp.model.Book

data class LibraryState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean
)