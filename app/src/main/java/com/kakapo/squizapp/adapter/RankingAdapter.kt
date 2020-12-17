package com.kakapo.squizapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.kakapo.squizapp.R
import com.kakapo.squizapp.model.Ranking
import com.kakapo.squizapp.ui.ScoreDetail

class RankingAdapter(
        options: FirebaseRecyclerOptions<Ranking>
) : FirebaseRecyclerAdapter<Ranking, RankingAdapter.ViewHolder>(options) {

    companion object{
        const val VIEW_USER = "viewUser"
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val userName: TextView = itemView.findViewById(R.id.txt_name)
        val score: TextView = itemView.findViewById(R.id.txt_score  )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_ranking, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Ranking) {
        holder.userName.text = model.userName
        holder.score.text = model.score.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ScoreDetail::class.java)
            intent.putExtra(VIEW_USER, model.userName)
            holder.itemView.context.startActivity(intent)
        }
    }
}