package com.kakapo.squizapp.model

data class Question(
    var Question: String? = null,
    var AnswerA: String? = null,
    var AnswerB: String? = null,
    var AnswerC: String? = null,
    var AnswerD: String? = null,
    var CorrectAnswer: String? = null,
    var CategoryId: String? = null,
    var IsImageQuestion: String? = null
)
