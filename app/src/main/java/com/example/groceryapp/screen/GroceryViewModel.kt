package com.example.groceryapp.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.groceryapp.database.GroceryDataClass
import com.example.groceryapp.repo.GroceryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GroceryViewModel(repo: GroceryRepository) : ViewModel() {
    private var repository=repo
    fun insert(item: GroceryDataClass) = viewModelScope.launch(Dispatchers.IO) { repository.insert(item) }
    fun delete(item: GroceryDataClass) = viewModelScope.launch(Dispatchers.IO) { repository.delete(item) }
    fun getAllItems() = repository.getAllItems()
}