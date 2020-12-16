package com.kakapo.squizapp.adapter

import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.kakapo.squizapp.model.QuestionScore

class ScoreAdapter(
        options: FirebaseRecyclerOptions<QuestionScore>
): FirebaseRecyclerAdapter<QuestionScore, ScoreAdapter.ViewHolder>(options) {
}