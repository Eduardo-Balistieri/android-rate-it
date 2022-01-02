package com.example.rateit.utils

import com.example.rateit.utils.Constants.HALF_STROKE

object PathUtils {
    fun interpolatePath(progress: Float, p1: Float, p2: Float, p3: Float): Float {
        val pp1 = parseCoord(p1)
        val pp2 = parseCoord(p2)
        val pp3 = parseCoord(p3)

        return when {
            progress < 5F -> when {
                pp1 == pp2 -> pp1
                progress == 0F -> pp1
                else -> linearInterpolation(0F, progress, 5F, pp1, pp2)
            }
            else -> when {
                pp2 == pp3 -> pp2
                progress == 5F -> pp2
                progress == 10F -> pp3
                else -> linearInterpolation(5F, progress, 10F, pp2, pp3)
            }
        }
    }

    private fun parseCoord(p: Float) = when {
        p < HALF_STROKE -> p + HALF_STROKE
        p > 100F - HALF_STROKE -> p - HALF_STROKE
        else -> p
    }

    private fun linearInterpolation(x1: Float, x2: Float, x3: Float, y1: Float, y2: Float): Float {
        return ((x2 - x1) * (y2 - y1)) / (x3 - x1) + y1
    }
}
