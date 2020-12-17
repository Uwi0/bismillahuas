package com.kakapo.squizapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.kakapo.squizapp.common.Common
import com.kakapo.squizapp.model.Question
import java.util.*
import com.kakapo.squizapp.R

class Start : AppCompatActivity() {

    private lateinit var btnPlaying: Button
    private lateinit var database: FirebaseDatabase
    private lateinit var question: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = FirebaseDatabase.getInstance()
        question = database.reference.child("Questions")
        btnPlaying = findViewById(R.id.btnPlay)

        loadQuestion(Common.categoryId)

        btnPlaying.setOnClickListener{
            val intent = Intent(this@Start, Playing::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadQuestion(categoryId: String){
        if(Common.questionList.size < 0){
            Common.questionList.clear()
        }

        question.orderByChild("CategoryId").equalTo(categoryId)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (dataSnapshot in snapshot.children) {
                            val ques = dataSnapshot.getValue(Question::class.java)
                            Common.questionList.add(ques!!)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
        Common.questionList.shuffle()
    }
}