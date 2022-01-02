package com.example.rateit.ui.emoji

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.rateit.utils.CustomPaint
import com.example.rateit.utils.PathUtils.interpolatePath
import com.example.rateit.utils.Constants.HALF_STROKE

class Mouth(context: Context, attrs: AttributeSet): View(context, attrs) {

    private var progress = 0F
    private val path = Path()
    private val scaleMatrix = Matrix()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.rewind()

        val p1 = object {
            val x = 0F + HALF_STROKE
            val y = interpolatePath(progress, 50F, 45F, 35F)
        }
        path.moveTo(p1.x, p1.y)

        val control1 = object {
            val x = 25F
            val y = interpolatePath(progress, 30F, 50F, 65F)
        }
        val p2 = object {
            val x = interpolatePath(progress, 40F, 50F, 60F)
            val y = interpolatePath(progress, 45F, 50F, 65F)
        }
        path.quadTo(control1.x, control1.y, p2.x, p2.y)


        val control2 = object {
            val x = interpolatePath(progress, 75F, 75F, 80F)
            val y = interpolatePath(progress, 15F, 50F, 65F)
        }
        val p3 = object {
            val x = 100F - HALF_STROKE
            val y = interpolatePath(progress, 50F, 45F, 55F)
        }
        path.quadTo(control2.x, control2.y, p3.x, p3.y)

        // ***

        val scaleX = this.measuredWidth / 100F
        val scaleY = this.measuredHeight / 100F

        scaleMatrix.setScale(scaleX, scaleY)
        path.transform(scaleMatrix)

        canvas?.drawPath(path, CustomPaint(Paint.Style.STROKE, Color.BLACK))
    }


    fun updateProgress(newProgress: Float) {
        progress = newProgress
    }
}
