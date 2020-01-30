package com.devandfun.mytravel.base.ui.views

import android.content.Context
import android.graphics.*
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
    private lateinit var colorsArray: IntArray
    private val bgPaint = Paint()
    private val textPaint = Paint()
    private val defaultStroke = Paint()
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
        defaultStroke.style = Paint.Style.STROKE
        defaultStroke.pathEffect = DashPathEffect(
            arrayOf(
                DASH_LENGTH_IN_PIXELS,
                DASH_SPACE_LENGTH_IN_PIXELS
            ).toFloatArray(), 0f
        )
        defaultStroke.strokeWidth = STROKE_WIDTH_IN_PIXELS
        defaultStroke.color = context.getColor(R.color.gray)
        bgPaint.color = colorsArray.random()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            if (name.isBlank()) {
                drawCircle(
                    width / 2f,
                    height / 2f,
                    width / 2f - STROKE_WIDTH_IN_PIXELS / 2f,
                    defaultStroke
                )
            } else {
                drawCircle(width / 2f, height / 2f, width / 2f, bgPaint)
                val textLength =
                if (name.length >= 2) {
                    2
                } else {
                    name.length
                }
                textPaint.getTextBounds(name, 0, textLength, textBounds)
                drawText(
                    name.toCharArray(),
                    0,
                    textLength,
                    (width / 2f - textBounds.width() / 2f),
                    height / 2f + textBounds.height() / 2f,
                    textPaint
                )
            }

        }
    }

    companion object {
        private const val DASH_LENGTH_IN_PIXELS = 48f
        private const val DASH_SPACE_LENGTH_IN_PIXELS = 16f
        private const val STROKE_WIDTH_IN_PIXELS = 8f
    }
}