package com.andriamparivonyschubert.henripotierapp.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.andriamparivonyschubert.henripotierapp.model.CartItem
import com.andriamparivonyschubert.henripotierapp.util.AppDatabase

class CartItemRepo(application: Application) {
    private val cartItemDao: CartItemDao
    private val allCartItems: LiveData<List<CartItem>>

    init {
        val database: AppDatabase = AppDatabase.getInstance(application)
        cartItemDao = database.cartItemDao()
        allCartItems = cartItemDao.getAll()
    }

    fun getAllCartItems(): LiveData<List<CartItem>> {
        return allCartItems
    }

    fun insert(cartItem: CartItem) {
        AppDatabase.databaseWriterExecutor.execute { cartItemDao.addCartItem(cartItem) }
    }

    fun get(isbn: String): LiveData<CartItem> {
        return cartItemDao.getByISBN(isbn)
    }

    fun update(updatedCartItem: CartItem) {
        AppDatabase.databaseWriterExecutor.execute {
            cartItemDao.updateCartItem(updatedCartItem)
        }
    }

    fun delete(deletedCartItem: CartItem) {
        AppDatabase.databaseWriterExecutor.execute {
            cartItemDao.deleteCartItem(deletedCartItem)
        }
    }
}