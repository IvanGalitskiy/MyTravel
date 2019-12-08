package com.devandfun.mytravel.base.ui.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.devandfun.mytravel.base.R
import java.util.*


class ProfileNameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defRes: Int = 0,
    defStyle: Int = 0
) : View(context, attrs, defRes, defStyle) {
    lateinit var colorsArray: IntArray
    private val bgPaint = Paint()
    private val textPaint = Paint()
    private val textBounds = Rect()
    var name: String = ""
        set(value) {
            field = value.toUpperCase(Locale.getDefault())
            invalidate()
        }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(R.styleable.ProfileNameView)
            val colors =
                typedArray.getResourceId(R.styleable.ProfileNameView_bg_colors, R.array.profileBg)
            colorsArray = typedArray.resources.getIntArray(colors)
            typedArray.recycle()
        }
        textPaint.color = Color.WHITE
        textPaint.style = Paint.Style.FILL
        textPaint.textSize =
            context.resources.getDimensionPixelSize(R.dimen.profile_name_text_size).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        bgPaint.color = colorsArray.random()
        canvas?.apply {
            drawCircle(width / 2f, height / 2f, width / 2f, bgPaint)
            textPaint.getTextBounds(name, 0, 2, textBounds)
//            drawRect(
//                width / 2f - textBounds.width() / 2f,
//                height / 2f - textPaint.textSize / 2f,
//                width / 2f + textBounds.width() / 2f,
//                height / 2f + textPaint.textSize / 2f,
//                textPaint
//            )
            drawText(
                name.toCharArray(),
                0,
                2,
                (width / 2f - textBounds.width() / 2f),
                height / 2f + textBounds.height() / 2f,
                textPaint
            )
        }
    }
}