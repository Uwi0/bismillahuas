package com.kakapo.squizapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.kakapo.squizapp.R
import com.kakapo.squizapp.common.Common
import com.kakapo.squizapp.model.Category
import com.kakapo.squizapp.ui.Coba
import com.kakapo.squizapp.ui.Start

class CategoryAdapter(
        val options: FirebaseRecyclerOptions<Category>
) : FirebaseRecyclerAdapter<Category, CategoryAdapter.ViewHolder>(options) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = itemView.findViewById(R.id.category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Category) {
        holder.name.text = model.Name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Start::class.java)
            Common.categoryId = this.getRef(position).key!!
            Common.categoryName = model.Name!!
            holder.itemView.context.startActivity(intent)
        }
    }
}