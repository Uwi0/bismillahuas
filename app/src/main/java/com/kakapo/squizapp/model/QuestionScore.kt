package com.kakapo.squizapp.model

data class QuestionScore(
    var Question_score: String? = null, 
    val User: String? = null,
    val Score: String? = null,
    val CategoryId: String? = null,
    val CategoryName: String? = null
) {
}