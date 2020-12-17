package com.kakapo.squizapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kakapo.squizapp.R
import com.kakapo.squizapp.adapter.RankingAdapter
import com.kakapo.squizapp.adapter.ScoreAdapter
import com.kakapo.squizapp.model.QuestionScore

class ScoreDetail: AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var questionScore: DatabaseReference
    private lateinit var scoreList: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: ScoreAdapter
    private var viewUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        database = FirebaseDatabase.getInstance()
        questionScore = database.reference.child("Question_Score")
        scoreList = findViewById(R.id.scoreList)
        scoreList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        scoreList.layoutManager = layoutManager

        if (intent != null){ //key "view user" ndek fragment ranking
            viewUser = intent.getStringExtra(RankingAdapter.VIEW_USER).toString()
        }

        if(viewUser.isNotEmpty()){
            loadScoreDetail(viewUser)
        }
    }

    private fun loadScoreDetail(viewUser: String){
        val options = FirebaseRecyclerOptions.Builder<QuestionScore>()
                .setQuery(questionScore, QuestionScore::class.java)
                .setLifecycleOwner(this)
                .build()
        adapter = ScoreAdapter(options)
        adapter.notifyDataSetChanged()
        scoreList.adapter = adapter

    }
}