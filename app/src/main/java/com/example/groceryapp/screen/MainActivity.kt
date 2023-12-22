package com.example.groceryapp.screen

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceryapp.R
import com.example.groceryapp.adapter.GroceryItemAdapter
import com.example.groceryapp.database.GroceryDataClass
import com.example.groceryapp.database.GroceryDatabase
import com.example.groceryapp.databinding.ActivityMainBinding
import com.example.groceryapp.databinding.DialogItemBinding
import com.example.groceryapp.repo.GroceryRepository


class MainActivity : AppCompatActivity(), GroceryItemAdapter.GroceryItemClickInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: GroceryViewModel
    private lateinit var list: List<GroceryDataClass>
    private lateinit var groceryItemAdapter: GroceryItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        list = ArrayList<GroceryDataClass>()
        groceryItemAdapter = GroceryItemAdapter(list, this)
        binding.itemsList.adapter = groceryItemAdapter
        binding.itemsList.layoutManager = LinearLayoutManager(this)
        val repo = GroceryRepository(GroceryDatabase.getInstance(this))
        val factory = GroceryViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[GroceryViewModel::class.java]
        viewModel.getAllItems().observe(this) {
            groceryItemAdapter.list = it
            groceryItemAdapter.notifyDataSetChanged()

        }
        binding.addButton.setOnClickListener { openDialog() }
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_item)
        val cancelButton = dialog.findViewById<Button>(R.id.cancel_Button)
        val addButton = dialog.findViewById<Button>(R.id.add_button)
        val name = dialog.findViewById<EditText>(R.id.item_name_edt_txt)
        val quantity = dialog.findViewById<EditText>(R.id.item_qyantity_edt_txt)
        val price = dialog.findViewById<EditText>(R.id.item_price_edt_txt)

        cancelButton.setOnClickListener { dialog.dismiss() }
        addButton.setOnClickListener {
            if (name.text.isNotEmpty() && quantity.text.isNotEmpty() && price.text.isNotEmpty()) {
                val item = GroceryDataClass(
                    name.text.toString(),
                    quantity.text.toString().toInt(),
                    price.text.toString().toInt()
                )
                viewModel.insert(item)
                Toast.makeText(applicationContext, "Item Added....", Toast.LENGTH_SHORT)
                    .show()
                groceryItemAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        else{
            Toast.makeText(applicationContext, "Please Enter all data", Toast.LENGTH_SHORT).show()
        }

    }
        dialog.show()
}

override fun onItemClick(groceryDataClass: GroceryDataClass) {
    viewModel.delete(groceryDataClass)
    groceryItemAdapter.notifyDataSetChanged()
    Toast.makeText(this, "Item has Deleted", Toast.LENGTH_SHORT).show()
}

}