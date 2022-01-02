package com.example.rateit.utils

import android.graphics.Paint
import com.example.rateit.utils.Constants.STROKE_WIDTH

class CustomPaint(paintStyle: Style, clr: Int): Paint() {
    init {
        style = paintStyle
        color = clr
        strokeWidth = STROKE_WIDTH
        strokeJoin = Join.ROUND
        strokeCap = Cap.ROUND
        isAntiAlias = true
    }
}
