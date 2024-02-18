package com.example.onlineshop.ui.catalog

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.onlineshop.R

data class Tag(
    val button: ImageButton,
    val text: TextView,
    val layout: ConstraintLayout,
    var state: BtnState = BtnState.UNCLICKED
) {
    fun setAppearance(context: Context) {
        when (state) {
            BtnState.CLICKED -> {
                layout.setBackgroundResource(R.drawable.tag_btn_dark_blur)
                text.setTextColor(Color.WHITE)
                button.visibility = View.VISIBLE
            }

            BtnState.UNCLICKED -> {
                layout.setBackgroundResource(R.drawable.tag_btn_light_grey)
                text.setTextColor(context.resources.getColor(R.color.middle_grey, context.theme))
                button.visibility = View.GONE
            }
        }
    }
}

enum class BtnState {
    CLICKED, UNCLICKED
}