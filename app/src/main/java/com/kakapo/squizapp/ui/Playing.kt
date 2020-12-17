package com.kakapo.squizapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.kakapo.squizapp.R
import com.kakapo.squizapp.common.Common
import kotlinx.android.synthetic.main.activity_playing.*

class Playing : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val INTERVAL: Long = 1000
        const val TIMEOUT: Long = 60000
        const val SCORE: String = "Score"
        const val TOTAL: String = "Total"
        const val CORRECT: String = "Correct"
    }

    private var progressValue = 60
    private var index = 0
    var score = 0
    private var thisQuestion = 0
    private var totalQuestion: Int = 0
    private var correctAnswer: Int = 0
    private lateinit var countDownTimer: CountDownTimer
    private lateinit var progressBar: ProgressBar
    private lateinit var questionImage: ImageView
    private lateinit var btnA: Button
    private lateinit var btnB: Button
    private lateinit var btnC: Button
    private lateinit var btnD: Button
    private lateinit var txtScore: TextView
    private lateinit var txtQuestionNum: TextView
    private lateinit var questionText: TextView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
            super.onCreate(savedInstanceState, persistentState)

        //set view
        txtScore = findViewById(R.id.txtScore)
        txtQuestionNum = findViewById(R.id.txtTotalQuestion)
        questionImage = findViewById(R.id.question_image)
        questionText = findViewById(R.id.question_text)


        progressBar = findViewById(R.id.progressBar)

        btnA = findViewById(R.id.btnAnswerA)
        btnB = findViewById(R.id.btnAnswerB)
        btnC = findViewById(R.id.btnAnswerC)
        btnD = findViewById(R.id.btnAnswerD)

        btnA.setOnClickListener(this)
        btnB.setOnClickListener(this)
        btnC.setOnClickListener(this)
        btnD.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        countDownTimer.cancel()
        if(index < totalQuestion){
            val clickedButton = view as Button
            if(clickedButton.text == Common.questionList[index].CorrectAnswer){
                score+= 25
                correctAnswer++
                showQuestion(++index)
            }else{
                score+= 0
                showQuestion(++index)
            }

            txtScore.text = ("$score")
        }
    }


    private fun showQuestion(index: Int){
        if(index < totalQuestion){
            thisQuestion++
            txtQuestionNum.text =("$thisQuestion/$totalQuestion")
            progressBar.progress = 0
            progressValue = 0

            if(Common.questionList[index].IsImageQuestion!!.equals(true)){ //lek error paling iki '<'
                //if is image
                Glide
                        .with(baseContext)
                        .load(Common.questionList[index].Question)
                        .into(question_image)
                question_image.visibility = View.VISIBLE
                questionText.visibility = View.INVISIBLE
            }else{
                questionText.text = Common.questionList[index].Question
                question_image.visibility = View.INVISIBLE
                questionText.visibility = View.VISIBLE
            }

            btnA.text = Common.questionList[index].AnswerA
            btnB.text = Common.questionList[index].AnswerB
            btnC.text = Common.questionList[index].AnswerC
            btnD.text = Common.questionList[index].AnswerD

            countDownTimer.start()
        }else{
            // jika pertanyaan terakhir
            val intent = Intent(this, Done::class.java)
            val dataSend = Bundle()
            dataSend.putInt(SCORE, score)
            dataSend.putInt(TOTAL, totalQuestion)
            dataSend.putInt(CORRECT, correctAnswer)
            intent.putExtras(dataSend)
            startActivity(intent)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        totalQuestion = Common.questionList.size
        countDownTimer = object : CountDownTimer(TIMEOUT, INTERVAL){
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = progressValue
                progressValue++
            }

            override fun onFinish() {
                countDownTimer.cancel()
                showQuestion(++index)
            }

        }
        showQuestion(++index)
    }
}
