package com.example.groceryapp.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.groceryapp.adapter.GroceryItemAdapter
import com.example.groceryapp.repo.GroceryRepository

@Suppress("UNCHECKED_CAST")
class GroceryViewModelFactory(private val repo:GroceryRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryViewModel::class.java)) {

            return GroceryViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}