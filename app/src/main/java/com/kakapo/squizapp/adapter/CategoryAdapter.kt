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
import com.kakapo.squizapp.interfaceApp.ItemClickListener
import com.kakapo.squizapp.model.Category

class CategoryAdapter(
        options: FirebaseRecyclerOptions<Category>
): FirebaseRecyclerAdapter<Category, CategoryAdapter.ViewHolder>(options) {

    lateinit var myFragment: View
    lateinit var listCategory: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        myFragment =LayoutInflater.from(parent.context).inflate(R.layout.fragment_category, parent, false)
        listCategory = myFragment.findViewById(R.id.listCategory)
        return ViewHolder(myFragment)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Category) {
        TODO("Not yet implemented")
    }

}