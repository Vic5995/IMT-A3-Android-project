package com.andriamparivonyschubert.henripotierapp.data

import com.andriamparivonyschubert.henripotierapp.model.Book
import retrofit2.http.GET

interface HenriPotierApi {
    @GET("/books")
    suspend fun  getBooks(): List<Book>
}