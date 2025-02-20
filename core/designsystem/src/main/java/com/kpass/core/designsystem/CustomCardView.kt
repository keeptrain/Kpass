package com.kpass.core.designsystem

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout

class CustomCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context,attrs) {

    private val startIcon: ImageView = ImageView(context)
//    private val textContent: TextView
    private val endIcon: ImageView = ImageView(context)


    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

//        LayoutInflater.from(context).inflate(com.kpass.feature.category.R.layout.empty_item,this, true)
    }

    override fun addView(child: View?) {
        super.addView(child)
    }


    override fun getBackground(): Drawable? {
        return super.background
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
    }

}