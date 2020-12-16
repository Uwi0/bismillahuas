package com.kakapo.squizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kakapo.squizapp.R
import com.kakapo.squizapp.interfaceApp.ItemClickListener
import com.kakapo.squizapp.model.Category

class CategoryFragment() : Fragment() {

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

        val options: FirebaseRecyclerOptions<Category> = FirebaseRecyclerOptions
                .Builder<Category>()
                .setQuery(categories, Category::class.java)
                .build()

        loadCategories(options)
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
    
    private fun loadCategories(options: FirebaseRecyclerOptions<Category>) {
        adapter = object : FirebaseRecyclerAdapter<Category, ViewHolder>(options) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                myFragment = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.fragment_category, parent, false)

                listCategory = myFragment.findViewById(R.id.listCategory)
                listCategory.setHasFixedSize(true)
                layoutManager = LinearLayoutManager(parent.context)
                return ViewHolder(myFragment)
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Category) {
                holder.categoryName.text = model.Name
                Glide.with(activity!!).load(model.Image).into(holder.categoryImage)

                holder.setItemOnclickListener(object : ItemClickListener {
                    override fun onClickListener(view: View, position: Int, isLongClick: Boolean) {
                        Toast.makeText(holder.itemView.context, "berhasil", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }

        adapter.notifyDataSetChanged()
        listCategory.adapter = adapter
    }

    companion object{
        fun newInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }

}