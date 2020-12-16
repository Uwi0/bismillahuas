package com.kakapo.squizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.kakapo.squizapp.R
import com.kakapo.squizapp.model.Category

class CategoryAdapter(
        options: FirebaseRecyclerOptions<Category>
) : FirebaseRecyclerAdapter<Category, CategoryAdapter.ViewHolder>(options) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = itemView.findViewById(R.id.category_name)
        val image: ImageView = itemView.findViewById(R.id.category_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Category) {
        holder.name.text = model.Name
    }
}