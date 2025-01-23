package com.usman.effectmvvm.viewmodel


import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usman.effectmvvm.utils.ImageFilterUtil
import com.usman.effectmvvm.utils.PhotoFilter


class MainViewModel : ViewModel() {

    private val _filteredBitmap = MutableLiveData<Bitmap>()
    val filteredBitmap: LiveData<Bitmap> get() = _filteredBitmap
    private var currentContrastValue = 1f // Initial contrast value
    private var currentBrightnessValue = 0f // Initial brightness value
    private var currentColdValue = 1f // Initial brightness value
    private var currentHotValue = 1f // Initial brightness value
    private var currentSaturateValue = 1f // Initial brightness value
    private var currentGrayScaleValue = 1f // Initial brightness value

    fun applyBrightnessEffect(originalBitmap: Bitmap, brightnessValue: Float) {
        val resultBitmap = ImageFilterUtil.applyBrightnessEffect(originalBitmap, brightnessValue)
        _filteredBitmap.value = resultBitmap
    }

    fun getCurrentBrightnessValue(): Float {
        return currentBrightnessValue
    }

    fun setCurrentBrightnessValue(brightnessValue: Float) {
        currentBrightnessValue = brightnessValue
    }

    fun applyContrastEffect(originalBitmap: Bitmap, contrastValue: Float) {
        val resultBitmap = ImageFilterUtil.applyContrastEffect(originalBitmap, contrastValue)
        _filteredBitmap.value = resultBitmap

    }

    fun getCurrentContrastValue(): Float {
        return currentContrastValue
    }

    fun setCurrentContrastValue(contrastValue: Float) {
        currentContrastValue = contrastValue
    }


    fun applyColdEffect(originalBitmap: Bitmap, coldValue: Float) {
        val bitmapSharp = ImageFilterUtil.applyColdEffect(originalBitmap, coldValue)
        _filteredBitmap.value = bitmapSharp
    }

    fun getCurrentColdValue(): Float {

        return currentColdValue
    }

    fun setCurrentColdValue(coldValue: Float) {
        currentColdValue = coldValue
    }

    fun applyHotEffect(originalBitmap: Bitmap, hotValue: Float) {
        val bitmapSharp = ImageFilterUtil.applyHotEffect(originalBitmap, hotValue)
        _filteredBitmap.value = bitmapSharp
    }

    fun getCurrentHotValue(): Float {

        return currentHotValue
    }

    fun setCurrentHotValue(hotValue: Float) {
        currentHotValue = hotValue
    }
    fun applyGrayScaleEffect(originalBitmap: Bitmap,grayScaleValue: Float) {
        val bitmapSharp = ImageFilterUtil.applyGrayScaleEffect(originalBitmap, grayScaleValue)
        _filteredBitmap.value = bitmapSharp
    }

    fun getCurrentGrayScaleValue(): Float {

        return currentGrayScaleValue
    }

    fun setCurrentGrayScaleValue(grayScaleValue: Float) {
        currentGrayScaleValue = grayScaleValue
    }
    fun applySaturateEffect(originalBitmap: Bitmap,saturateValue: Float) {
        val bitmapSharp = ImageFilterUtil.applySaturateEffect(originalBitmap, saturateValue)
        _filteredBitmap.value = bitmapSharp
    }
    fun getCurrentSaturateValue(): Float {

        return currentSaturateValue
    }

    fun setCurrentSaturateValue(saturateValue: Float) {
        currentSaturateValue = saturateValue
    }



}
