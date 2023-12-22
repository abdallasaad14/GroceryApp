package com.example.groceryapp.repo

import com.example.groceryapp.database.GroceryDataClass
import com.example.groceryapp.database.GroceryDatabase

class GroceryRepository( val db:GroceryDatabase) {
    suspend fun insert(item:GroceryDataClass)=db.sleepDatabaseDao.insert(item)
    suspend fun delete(item: GroceryDataClass)=db.sleepDatabaseDao.delete(item)
    fun getAllItems()=db.sleepDatabaseDao.getAllItems()

}