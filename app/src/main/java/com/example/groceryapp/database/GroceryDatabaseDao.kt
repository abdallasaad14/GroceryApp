package com.example.groceryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GroceryDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(groceryDataClass: GroceryDataClass)

    @Update
    fun update(groceryDataClass: GroceryDataClass)

    @Delete
    fun delete(groceryDataClass: GroceryDataClass)

    @Query("select * from grocery_table")
    fun getAllItems():LiveData<List<GroceryDataClass>>
}