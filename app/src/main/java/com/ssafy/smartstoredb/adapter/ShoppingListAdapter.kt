package com.ssafy.smartstoredb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.smartstoredb.R


class ShoppingListAdapter :RecyclerView.Adapter<ShoppingListAdapter.ShoppingListHolder>(){

    inner class ShoppingListHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindInfo(){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_shopping_list, parent, false)
        return ShoppingListHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListHolder, position: Int) {
        holder.bindInfo()
    }

    override fun getItemCount(): Int {
        return 10
    }
}

