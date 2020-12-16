package com.kakapo.squizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kakapo.squizapp.interfaceApp.ItemClickListener
import com.kakapo.squizapp.R
import com.kakapo.squizapp.model.Category

class CategoryFragment(var options: FirebaseRecyclerOptions<Category>) : Fragment() {

    lateinit var myFragment: View
    lateinit var listCategory: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter:FirebaseRecyclerAdapter<Category, ViewHolder>

    lateinit var database: FirebaseDatabase
    lateinit var categories: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        categories =database.getReference("Category")
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        lateinit var categoryImage: ImageView
        lateinit var categoryName: TextView

        private lateinit var itemClickListener: ItemClickListener

        init {
            categoryImage = itemView.findViewById(R.id.category_image)
            categoryName = itemView.findViewById(R.id.category_name)

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            itemClickListener.onClickListener(view, adapterPosition, false)
        }

        fun setItemOnclickListener(itemClickListener: ItemClickListener){
            this.itemClickListener = itemClickListener
        }

    }
    
    private fun loadCategories() {

    }

    companion object{
        fun newInstance(): Companion {
            return CategoryFragment
        }
    }

}