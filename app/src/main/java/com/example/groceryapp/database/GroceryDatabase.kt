package com.example.groceryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile

@Database(entities = [GroceryDataClass::class], version = 1, exportSchema = false)
abstract class GroceryDatabase : RoomDatabase() {
    abstract val sleepDatabaseDao: GroceryDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: GroceryDatabase? = null
        fun getInstance(context: Context): GroceryDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GroceryDatabase::class.java,
                        "grocery_data_history"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}