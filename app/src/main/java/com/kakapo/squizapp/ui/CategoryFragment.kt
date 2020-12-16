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

    lateinit var database:FirebaseDatabase
    lateinit var category: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        category =database.getReference("Category")
    }

    companion object{
        fun newInstance(): Companion {
            return CategoryFragment
        }
    }

}