package com.kakapo.squizapp.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.kakapo.squizapp.model.QuestionScore

class ScoreAdapter(
        options: FirebaseRecyclerOptions<QuestionScore>
): FirebaseRecyclerAdapter<QuestionScore, ScoreAdapter.ViewHolder>(options) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: QuestionScore) {
        TODO("Not yet implemented")
    }
}