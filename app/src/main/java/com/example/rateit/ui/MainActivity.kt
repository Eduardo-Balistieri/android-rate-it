package com.example.rateit.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rateit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeBackground(0F)
        setupSlider()
    }

    private fun changeBackground(value: Float) {
        val r = interpolateColor(value, 225, 225, 182)
        val g = interpolateColor(value, 192, 214, 218)
        val b = interpolateColor(value, 244, 220, 240)

        val hex = String.format("#%02x%02x%02x", r, g, b)
        binding.root.setBackgroundColor(Color.parseColor(hex))
    }

    private fun setupSlider() {
        binding.slider.setLabelFormatter { value: Float ->
            value.toInt().toString()
        }

        binding.slider.addOnChangeListener { _, value, _ ->
            changeBackground(value)

            binding.leftEye.apply {
                updateProgress(value)
                invalidate()
            }
            binding.rightEye.apply {
                updateProgress(value)
                invalidate()
            }
            binding.mouth.apply {
                updateProgress(value)
                invalidate()
            }
        }
    }


    private fun interpolateColor(progress: Float, c1: Int, c2: Int, c3: Int): Int {
        return when {
            progress < 5F -> when {
                c1 == c2 -> c1
                progress == 0F -> c1
                else -> linearInterpolation(0F, progress, 5F, c1, c2).toInt()
            }
            else -> when {
                c2 == c3 -> c2
                progress == 5F -> c2
                progress == 10F -> c3
                else -> linearInterpolation(5F, progress, 10F, c2, c3).toInt()
            }
        }
    }

    private fun linearInterpolation(x1: Float, x2: Float, x3: Float, y1: Int, y2: Int): Float {
        return ((x2 - x1) * (y2 - y1)) / (x3 - x1) + y1
    }
}
