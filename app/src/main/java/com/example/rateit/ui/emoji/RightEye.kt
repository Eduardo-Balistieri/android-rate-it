package com.example.rateit.ui.emoji

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.rateit.utils.CustomPaint
import com.example.rateit.utils.PathUtils.interpolatePath
import com.example.rateit.utils.Constants.HALF_STROKE

class RightEye(context: Context, attrs: AttributeSet): View(context, attrs) {

    private var progress = 0F

    private val fPath = Path()  // fill
    private val cPath = Path()  // eye "pupil"
    private val sPath = Path()  // stroke

    private val scaleMatrix = Matrix()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        fPath.rewind()
        sPath.rewind()
        cPath.rewind()


        val p1 = object {
            val x = 0F + HALF_STROKE
            val y = interpolatePath(progress, 0F, 5F, 50F)
        }
        fPath.moveTo(p1.x, p1.y)

        val p2 = object {
            val x = interpolatePath(progress, 60F, 65F, 50F)
            val y = interpolatePath(progress, 100F, 55F, 100F)
        }

        val control1 = object {
            val x = 0F + HALF_STROKE
            val y = interpolatePath(progress, 0F, 45F, 80F)
        }
        val control2 = object {
            val x = interpolatePath(progress, 35F, 50F, 30F)
            val y = p2.y
        }
        fPath.cubicTo(control1.x, control1.y, control2.x, control2.y, p2.x, p2.y)


        val p3 = object {
            val x = 100F - HALF_STROKE
            val y = interpolatePath(progress, 5F, 25F, 50F)
        }

        val control3 = object {
            val x = interpolatePath(progress, 85F, 80F, 75F)
            val y = p2.y
        }
        val control4 = object {
            val x = interpolatePath(progress, 90F, 105F, 100F)
            val y = interpolatePath(progress, 50F, 30F, 75F)
        }
        fPath.cubicTo(control3.x, control3.y, control4.x, control4.y, p3.x, p3.y)


        val control5 = object {
            val x = interpolatePath(progress, 80F, 75F, 100F)
            val y = interpolatePath(progress, 5F, 15F, -15F)
        }
        val control6 = object {
            val x = interpolatePath(progress, 50F, 0F, 0F)
            val y = interpolatePath(progress, 10F, 5F, -15F)
        }
        fPath.cubicTo(control5.x, control5.y, control6.x, control6.y, p1.x, p1.y)

        // ***

        val scaleX = this.measuredWidth / 100F
        val scaleY = this.measuredHeight / 100F

        scaleMatrix.setScale(scaleX, scaleY)
        fPath.transform(scaleMatrix)

        fPath.close()
        canvas?.drawPath(fPath, CustomPaint(Paint.Style.FILL, Color.WHITE))


        // circle
        cPath.addCircle(
            interpolatePath(progress, 55F, 45F, 50F),
            interpolatePath(progress, 50F, 30F, 50F),
            3F,
            Path.Direction.CW
        )
        cPath.transform(scaleMatrix)
        cPath.close()
        canvas?.drawPath(cPath, CustomPaint(Paint.Style.FILL, Color.BLACK))


        // stroke
        sPath.moveTo(p1.x, p1.y)
        sPath.cubicTo(control1.x, control1.y, control2.x, control2.y, p2.x, p2.y)
        sPath.cubicTo(control3.x, control3.y, control4.x, control4.y, p3.x, p3.y)
        sPath.cubicTo(control5.x, control5.y, control6.x, control6.y, p1.x, p1.y)

        sPath.transform(scaleMatrix)
        sPath.close()
        canvas?.drawPath(sPath, CustomPaint(Paint.Style.STROKE, Color.BLACK))
    }


    fun updateProgress(newProgress: Float) {
        progress = newProgress
    }
}
