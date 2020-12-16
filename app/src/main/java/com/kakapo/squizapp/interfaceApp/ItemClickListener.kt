package com.kakapo.squizapp.interfaceApp

import android.view.View

interface ItemClickListener {
    fun onClickListener(view: View, position: Int, isLongClick: Boolean)
}