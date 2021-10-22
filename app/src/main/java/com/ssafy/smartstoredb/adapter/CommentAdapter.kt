package com.ssafy.smartstoredb.adapter

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.smartstoredb.ApplicationClass
import com.ssafy.smartstoredb.R
import com.ssafy.smartstoredb.dto.Comment


class CommentAdapter(val context: Context, val list:List<Comment> ) :RecyclerView.Adapter<CommentAdapter.CommentHolder>(){

    inner class CommentHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindInfo(data :Comment){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_comment, parent, false)
        return CommentHolder(view)
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.bindInfo(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

