package com.usman.effectmvvm.utils


import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint

object ImageFilterUtil {

    fun applyContrastEffect(originalBitmap: Bitmap, contrastValue: Float): Bitmap {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(
            floatArrayOf(
                contrastValue, 0f, 0f, 0f, 0f,
                0f, contrastValue, 0f, 0f, 0f,
                0f, 0f, contrastValue, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint()
        paint.colorFilter = colorFilter

        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, originalBitmap.config
        )

        val canvas = Canvas(resultBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return resultBitmap
    }

    fun applyBrightnessEffect(originalBitmap: Bitmap, brightnessValue: Float): Bitmap {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(
            floatArrayOf(
                1F, 0F, 0F, 0F, brightnessValue,
                0F, 1F, 0F, 0F, brightnessValue,
                0F, 0F, 1F, 0F, brightnessValue,
                0F, 0F, 0F, 1F, 0F
            )
        )

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint()
        paint.colorFilter = colorFilter

        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, originalBitmap.config
        )

        val canvas = Canvas(resultBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return resultBitmap
    }


    fun applyHotEffect(originalBitmap: Bitmap, warmthLevel: Float): Bitmap {
        val colorMatrix = ColorMatrix()

        // Increase the red channel for warmth
        colorMatrix.set(
            floatArrayOf(
                warmthLevel, 0f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f, 0f,
                0f, 0f, 1f, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint()
        paint.colorFilter = colorFilter

        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, originalBitmap.config
        )

        val canvas = Canvas(resultBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return resultBitmap
    }

    fun applyColdEffect(originalBitmap: Bitmap, coolingLevel: Float): Bitmap {
        val colorMatrix = ColorMatrix()

        // Increase the blue channel for a cooling effect
        colorMatrix.set(
            floatArrayOf(
                1f, 0f, 0f, 0f, 0f,
                0f, 1f, 0f, 0f, 0f,
                0f, 0f, coolingLevel, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint()
        paint.colorFilter = colorFilter

        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, originalBitmap.config
        )

        val canvas = Canvas(resultBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return resultBitmap
    }

    fun applyGrayScaleEffect(originalBitmap: Bitmap, value: Float): Bitmap {
        val colorMatrix = ColorMatrix()

        // Set the saturation to 0 for a grayscale effect
        colorMatrix.setSaturation(value)

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint()
        paint.colorFilter = colorFilter

        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, originalBitmap.config
        )

        val canvas = Canvas(resultBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return resultBitmap
    }

    fun applySaturateEffect(originalBitmap: Bitmap, saturationLevel: Float): Bitmap {
        val colorMatrix = ColorMatrix()

        // Adjust the saturation level
        colorMatrix.setSaturation(saturationLevel)

        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        val paint = Paint()
        paint.colorFilter = colorFilter

        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, originalBitmap.config
        )

        val canvas = Canvas(resultBitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return resultBitmap
    }

    fun applySharpnessEffect(originalBitmap: Bitmap, sharpnessValue: Float): Bitmap {
        // Example sharpening kernel
        val kernel = arrayOf(
            floatArrayOf(-0.1f, -0.1f, -0.1f),
            floatArrayOf(-0.1f, 2.0f + sharpnessValue, -0.1f),
            floatArrayOf(-0.1f, -0.1f, -0.1f)
        )

        return applyConvolutionFilter(originalBitmap, kernel)
    }

    private fun applyConvolutionFilter(originalBitmap: Bitmap, kernel: Array<FloatArray>): Bitmap {
        val kernelSize = kernel.size
        val kernelRadius = kernelSize / 2

        val result = Bitmap.createBitmap(
            originalBitmap.width, originalBitmap.height, originalBitmap.config
        )

        for (y in kernelRadius until originalBitmap.height - kernelRadius) {
            for (x in kernelRadius until originalBitmap.width - kernelRadius) {
                var sumRed = 0f
                var sumGreen = 0f
                var sumBlue = 0f

                for (ky in 0 until kernelSize) {
                    for (kx in 0 until kernelSize) {
                        val pixel = originalBitmap.getPixel(
                            x - kernelRadius + kx,
                            y - kernelRadius + ky
                        )

                        val weight = kernel[ky][kx]

                        sumRed += weight * Color.red(pixel)
                        sumGreen += weight * Color.green(pixel)
                        sumBlue += weight * Color.blue(pixel)
                    }
                }

                val finalRed = sumRed.coerceIn(0f, 255f).toInt()
                val finalGreen = sumGreen.coerceIn(0f, 255f).toInt()
                val finalBlue = sumBlue.coerceIn(0f, 255f).toInt()

                result.setPixel(x, y, Color.rgb(finalRed, finalGreen, finalBlue))
            }
        }

        return result
    }


}
