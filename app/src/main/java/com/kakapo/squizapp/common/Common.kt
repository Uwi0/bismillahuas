package com.kakapo.squizapp.common

import com.kakapo.squizapp.model.Question
import com.kakapo.squizapp.model.User

class Common {
    companion object{
        lateinit var categoryId: String
        lateinit var categoryName: String
        lateinit var currentUser: User
        var questionList: ArrayList<Question> = ArrayList()
    }
}