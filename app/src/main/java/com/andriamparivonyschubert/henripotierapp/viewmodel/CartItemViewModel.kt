package com.andriamparivonyschubert.henripotierapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andriamparivonyschubert.henripotierapp.data.CartItemRepo
import com.andriamparivonyschubert.henripotierapp.model.CartItem

class CartItemViewModel(application: Application): AndroidViewModel(application) {
    private val allCartItems: LiveData<List<CartItem>>

    init {
        repository = CartItemRepo(application)
        allCartItems = repository.getAllCartItems()
    }

    companion object {
        lateinit var repository: CartItemRepo
        fun insert(cartItem: CartItem) {
            repository.insert(cartItem)
        }

        fun get(isbn: String): LiveData<CartItem> {
            return repository.get(isbn)
        }

        fun update(cartItem: CartItem) {
            repository.update(cartItem)
        }

        fun delete(cartItem: CartItem) {
            repository.delete(cartItem)
        }
    }

}