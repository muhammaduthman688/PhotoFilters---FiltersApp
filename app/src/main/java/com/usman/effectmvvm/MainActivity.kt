package com.usman.effectmvvm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.usman.effectmvvm.databinding.ActivityMainBinding
import com.usman.effectmvvm.utils.FilterModel
import com.usman.effectmvvm.utils.PhotoFilter
import com.usman.effectmvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]




        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.img)
        binding.imageView.setImageBitmap(originalBitmap)


        binding.sbBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val brightnessValue =
                    progress / 100f * 120f // (0f to 120f)
                viewModel.setCurrentBrightnessValue(brightnessValue)
                viewModel.applyBrightnessEffect(originalBitmap, brightnessValue)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })
        binding.sbCold.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val coldValue =
                    progress / 100f * 2f + 1f //  (1f to 3f)
                viewModel.setCurrentColdValue(coldValue)
                viewModel.applyColdEffect(originalBitmap, coldValue)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })

        binding.sbContrast.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val contrastValue =
                    progress / 100f * 2f + 1f //  (1f to 3f)
                viewModel.setCurrentContrastValue(contrastValue)
                viewModel.applyContrastEffect(originalBitmap, contrastValue)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })

        binding.sbHot.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val hotValue = progress / 100f * 0.5f + 1f//1.5 maximum
                viewModel.setCurrentHotValue(hotValue)
                viewModel.applyHotEffect(originalBitmap, hotValue)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })
        binding.sbGrayScale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val grayScaleValue = progress / 100f * -1f + 1f// (-1f to 1f)
                viewModel.setCurrentSaturateValue(grayScaleValue)
                viewModel.applyGrayScaleEffect(originalBitmap, grayScaleValue)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })
        binding.sbSaturate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                val saturateValue = progress / 100f * 1f + 1f//miximum 2f(1f to 2f)
                viewModel.setCurrentSaturateValue(saturateValue)
                viewModel.applySaturateEffect(originalBitmap, saturateValue)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })

        viewModel.filteredBitmap.observe(this) { filteredBitmap ->
            binding.imageView.setImageBitmap(filteredBitmap)
        }


        val filterList = listOf(
            FilterModel(PhotoFilter.NONE, R.drawable.img),
            FilterModel(PhotoFilter.BRIGHTNESS, R.drawable.img),
            FilterModel(PhotoFilter.COLD, R.drawable.img),
            FilterModel(PhotoFilter.CONTRAST, R.drawable.img),
            FilterModel(PhotoFilter.HOT, R.drawable.img),
            FilterModel(PhotoFilter.GRAY_SCALE, R.drawable.img),
            FilterModel(PhotoFilter.SATURATE, R.drawable.img),

            )
        val filterAdapter = FilterAdapter(filterList) {
            filter ->
            applyFilter(originalBitmap, filter)
        }
        binding.linearLayout.adapter = filterAdapter
    }

    private fun applyFilter(originalBitmap: Bitmap, filter: FilterModel) {

        when (filter.name) {
            PhotoFilter.NONE -> {
                binding.imageView.setImageBitmap(originalBitmap)
                none()
            }

            PhotoFilter.CONTRAST -> {
                viewModel.applyContrastEffect(originalBitmap, viewModel.getCurrentContrastValue())
                sbContrast()
            }

            PhotoFilter.BRIGHTNESS -> {
                viewModel.applyBrightnessEffect(
                    originalBitmap,
                    viewModel.getCurrentBrightnessValue()
                )
                sbBrightness()
            }

            PhotoFilter.COLD -> {
                viewModel.applyColdEffect(originalBitmap, viewModel.getCurrentColdValue())
                sbCold()
            }

            PhotoFilter.HOT -> {
                viewModel.applyHotEffect(originalBitmap, viewModel.getCurrentHotValue())
                sbHot()
            }

            PhotoFilter.GRAY_SCALE -> {
                viewModel.applyGrayScaleEffect(originalBitmap, viewModel.getCurrentGrayScaleValue())
                sbGrayScale()
            }

            PhotoFilter.SATURATE -> {
                viewModel.applySaturateEffect(originalBitmap, viewModel.getCurrentSaturateValue())
                sbSaturate()
            }

            else -> {}
        }

    }


    private fun none() {
        binding.sbContrast.visibility = SeekBar.GONE
        binding.sbBrightness.visibility = SeekBar.GONE
        binding.sbCold.visibility = SeekBar.GONE
        binding.sbHot.visibility = SeekBar.GONE
        binding.sbSaturate.visibility = SeekBar.GONE
        binding.sbGrayScale.visibility = SeekBar.GONE

    }

    private fun sbBrightness() {
        binding.sbContrast.visibility = SeekBar.GONE
        binding.sbBrightness.visibility = SeekBar.VISIBLE
        binding.sbCold.visibility = SeekBar.GONE
        binding.sbHot.visibility = SeekBar.GONE
        binding.sbSaturate.visibility = SeekBar.GONE
        binding.sbGrayScale.visibility = SeekBar.GONE

    }

    private fun sbContrast() {
        binding.sbContrast.visibility = SeekBar.VISIBLE
        binding.sbBrightness.visibility = SeekBar.GONE
        binding.sbCold.visibility = SeekBar.GONE
        binding.sbHot.visibility = SeekBar.GONE
        binding.sbSaturate.visibility = SeekBar.GONE
        binding.sbGrayScale.visibility = SeekBar.GONE

    }

    private fun sbCold() {
        binding.sbContrast.visibility = SeekBar.GONE
        binding.sbBrightness.visibility = SeekBar.GONE
        binding.sbCold.visibility = SeekBar.VISIBLE
        binding.sbHot.visibility = SeekBar.GONE
        binding.sbSaturate.visibility = SeekBar.GONE
        binding.sbGrayScale.visibility = SeekBar.GONE
    }

    private fun sbHot() {
        binding.sbContrast.visibility = SeekBar.GONE
        binding.sbBrightness.visibility = SeekBar.GONE
        binding.sbCold.visibility = SeekBar.GONE
        binding.sbHot.visibility = SeekBar.VISIBLE
        binding.sbSaturate.visibility = SeekBar.GONE
        binding.sbGrayScale.visibility = SeekBar.GONE
    }

    private fun sbGrayScale() {
        binding.sbContrast.visibility = SeekBar.GONE
        binding.sbBrightness.visibility = SeekBar.GONE
        binding.sbCold.visibility = SeekBar.GONE
        binding.sbHot.visibility = SeekBar.GONE
        binding.sbSaturate.visibility = SeekBar.GONE
        binding.sbGrayScale.visibility = SeekBar.VISIBLE
    }

    private fun sbSaturate() {
        binding.sbContrast.visibility = SeekBar.GONE
        binding.sbBrightness.visibility = SeekBar.GONE
        binding.sbCold.visibility = SeekBar.GONE
        binding.sbHot.visibility = SeekBar.GONE
        binding.sbSaturate.visibility = SeekBar.VISIBLE
        binding.sbGrayScale.visibility = SeekBar.GONE
    }
}