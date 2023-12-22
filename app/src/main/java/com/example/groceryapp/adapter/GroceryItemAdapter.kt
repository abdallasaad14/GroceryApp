package com.example.groceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.database.GroceryDataClass
import com.example.groceryapp.databinding.RecycleViewItemBinding

class GroceryItemAdapter(var list: List<GroceryDataClass>,val groceryItemClickInterface: GroceryItemClickInterface):RecyclerView.Adapter<GroceryItemAdapter.GroceryViewHolder>() {


    inner class GroceryViewHolder(val binding:RecycleViewItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
    return GroceryViewHolder(RecycleViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
    val item=list[position]
        holder.binding.itemName.text=item.name
        holder.binding.itemQuantity.text=item.count.toString()
        holder.binding.itemRate.text=item.price.toString()
        holder.binding.itemCost.text=(item.price*item.count).toString()
        holder.binding.itemDelete.setOnClickListener { groceryItemClickInterface.onItemClick(item) }
    }

    override fun getItemCount()=list.size


    interface GroceryItemClickInterface{fun onItemClick(groceryDataClass: GroceryDataClass)}

}
