package com.andriamparivonyschubert.henripotierapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andriamparivonyschubert.henripotierapp.model.CartItem

@Dao
interface CartItemDao {
    @Query("DELETE FROM cartitem")
    fun deleteAll()

    @Query("SELECT * FROM cartitem")
    fun getAll(): LiveData<List<CartItem>>

    @Query("SELECT * FROM cartitem WHERE isbn = :isbn")
    fun getByISBN(isbn: String): LiveData<CartItem>

    @Update
    fun updateCartItem(updatedCartItem: CartItem)

    @Delete
    fun deleteCartItem(deletedCartItem: CartItem)

    @Insert
    fun addCartItem(addedCartItem: CartItem)
}