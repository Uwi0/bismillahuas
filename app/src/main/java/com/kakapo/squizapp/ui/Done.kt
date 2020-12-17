package com.kakapo.squizapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kakapo.squizapp.R
import com.kakapo.squizapp.common.Common
import com.kakapo.squizapp.model.QuestionScore
import kotlinx.android.synthetic.main.activity_done.*
import java.lang.String

class Done : AppCompatActivity(){

    private lateinit var btnTryAgain: Button
    private lateinit var textResultScore : TextView
    private lateinit var getTextResultQuestion: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var database: FirebaseDatabase
    private lateinit var questionScore: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        database = FirebaseDatabase.getInstance()
        questionScore  = database.reference.child("Question_Score")
        progressBar = findViewById(R.id.doneProgressBar)
        textResultScore = findViewById(R.id.txtTotalScore)
        getTextResultQuestion = findViewById(R.id.txtTotalQuestion)
        btnTryAgain = findViewById(R.id.btnTryAgain)

        btnTryAgain.setOnClickListener {
            val intent = Intent(this@Done, Home::class.java)
            startActivity(intent)
            finish()
        }

        val extra = intent.extras
        if (extra != null){
            val score = extra.get(Playing.SCORE)
            val totalQuestion = extra.get(Playing.TOTAL)
            val correctAnswer = extra.get(Playing.CORRECT)

            textResultScore.text = ("SCORE: $score")
            textResultScore.text =("PASSED: $correctAnswer/$totalQuestion")

            progressBar.max = totalQuestion as Int
            progressBar.progress = correctAnswer as Int

            questionScore.child(("${Common.currentUser.userName}_${Common.categoryId}"))
                    .setValue(QuestionScore(String.format("%s_%s", Common.currentUser.userName,
                            Common.categoryId),
                            Common.currentUser.userName,
                            score.toString(),
                            Common.categoryId,
                            Common.categoryName
                    ))

        }
    }
}
