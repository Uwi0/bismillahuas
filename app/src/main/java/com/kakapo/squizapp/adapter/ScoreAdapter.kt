package com.kakapo.squizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.kakapo.squizapp.R
import com.kakapo.squizapp.model.QuestionScore

class ScoreAdapter(
        options: FirebaseRecyclerOptions<QuestionScore>
): FirebaseRecyclerAdapter<QuestionScore, ScoreAdapter.ViewHolder>(options) {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val questionScore: TextView = itemView.findViewById(R.id.txt_score)
        val questionName: TextView = itemView.findViewById(R.id.txt_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_detail_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: QuestionScore) {
        holder.questionName.text = model.CategoryName
        holder.questionScore.text = model.Question_score
    }
}