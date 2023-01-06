package com.andriamparivonyschubert.henripotierapp.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.andriamparivonyschubert.henripotierapp.data.BookDao
import com.andriamparivonyschubert.henripotierapp.data.CartItemDao
import com.andriamparivonyschubert.henripotierapp.model.Book
import com.andriamparivonyschubert.henripotierapp.model.CartItem
import java.util.concurrent.Executors

@Database(entities = [CartItem::class, Book::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cartItemDao(): CartItemDao
    abstract fun bookDao(): BookDao

    companion object {
        private var instance: AppDatabase? = null

        const val NUMBER_OF_THREADS = 4
        const val DATABASE_NAME = "app_database"
        val databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                databaseWriterExecutor.execute(Runnable {

                    // invoke Dao, and write
                    val bookDao: BookDao = instance!!.bookDao()
                    bookDao.deleteAll()
                    val cartItemDao: CartItemDao = instance!!.cartItemDao()
                    cartItemDao.deleteAll()
                })
            }
        }

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME)
                    .addCallback(sRoomDatabaseCallback)
                    .build()

            return instance!!
        }

    }
}