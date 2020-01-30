package com.devandfun.mytravel.base.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import com.devandfun.mytravel.base.R
import kotlinx.android.synthetic.main.view_input_section.view.*

class InputSectionView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defRes) {
    init {
        LayoutInflater.from(context).inflate(R.layout.view_input_section, this)
        if (attrs != null) {
            setAttrs(attrs)
        }
    }

    private fun setAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InputSectionView)
        vSectionName.text = typedArray.getString(R.styleable.InputSectionView_is_section_name)
        vSectionField.hint = typedArray.getString(R.styleable.InputSectionView_is_section_hint)
        typedArray.recycle()
    }

    fun afterTextChanged(afterTextChanged: (String) -> Unit) {
        vSectionField.doAfterTextChanged { afterTextChanged.invoke(it.toString()) }
    }
}