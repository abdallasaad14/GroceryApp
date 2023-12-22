package com.example.groceryapp.database

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("grocery_table")
data class GroceryDataClass(
    @ColumnInfo("itemName")
    val name: String,
    @ColumnInfo("itemCount")
    val count: Int,
    @ColumnInfo("itemPrice")
    val price: Int,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}