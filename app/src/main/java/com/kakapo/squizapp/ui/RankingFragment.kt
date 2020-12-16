package com.kakapo.squizapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kakapo.squizapp.model.Category

class RankingFragment : Fragment() {

    lateinit var myFragment: View
    lateinit var rankingList: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: FirebaseRecyclerAdapter<Category, CategoryFragment.ViewHolder>
    lateinit var database: FirebaseDatabase
    lateinit var questionScore: DatabaseReference
    lateinit var rankingTable: DatabaseReference

    companion object{
        fun newInstance() : RankingFragment{
            return RankingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        questionScore = database.getReference("Question_Score")
        rankingTable = database.getReference("Rangking")


    }
}