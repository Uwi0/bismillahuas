package com.kakapo.squizapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.kakapo.squizapp.R
import com.kakapo.squizapp.adapter.CategoryAdapter
import com.kakapo.squizapp.common.Common
import com.kakapo.squizapp.model.Category
import com.kakapo.squizapp.model.User


class CategoryFragment : Fragment() {

    private lateinit var listCategory: RecyclerView
    private lateinit var adapter: CategoryAdapter
    private lateinit var mBase: DatabaseReference
    private lateinit var myFragment: View
    private lateinit var inflater: LayoutInflater
    private lateinit var container: ViewGroup
    private lateinit var layoutManager: RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myFragment = inflater.inflate(R.layout.fragment_category, container, false)

        listCategory = myFragment.findViewById(R.id.listCategory)
//        listCategory.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(container!!.context)
        listCategory.layoutManager = layoutManager
        mBase = FirebaseDatabase.getInstance().reference.child("Category")
        loadCategory()

        return myFragment
    }

    private fun loadCategories(){

        val options:FirebaseRecyclerOptions<Category> = FirebaseRecyclerOptions
                .Builder<Category>()
                .setQuery(mBase, Category::class.java)
                .build()
        adapter = CategoryAdapter(options)
        listCategory.adapter = adapter
    }

    private fun loadCategory(){
        val options = FirebaseRecyclerOptions.Builder<Category>()
            .setQuery(mBase,Category::class.java)
            .setLifecycleOwner(this)
            .build()

        adapter = CategoryAdapter(options)
        listCategory.adapter = adapter
    }

    companion object{
        fun newInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }

}