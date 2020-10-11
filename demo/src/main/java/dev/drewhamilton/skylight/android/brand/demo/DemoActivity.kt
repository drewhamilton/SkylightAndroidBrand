package dev.drewhamilton.skylight.android.brand.demo

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import dev.drewhamilton.skylight.android.brand.demo.databinding.BottomSheetBinding
import dev.drewhamilton.skylight.android.brand.demo.databinding.DemoBinding

class DemoActivity : AppCompatActivity() {
    private var isBottomSheetShowing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonsSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.bottomSheetButton.isEnabled = isChecked
            binding.errorBannerButton.isEnabled = isChecked
            binding.nothingButton.isEnabled = isChecked
        }

        binding.bottomSheetButton.setOnClickListener { showBottomSheet() }

//        errorBanner.setPrimaryButtonOnClickListener {
//            TryAgain { motionLayout.transitionToEnd() }.start()
//            motionLayout.transitionToStart()
//        }
//        errorBanner.setSecondaryButtonOnClickListener { motionLayout.transitionToStart() }
//        binding.errorBannerButton.setOnClickListener { motionLayout.transitionToEnd() }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState != null) {
//            val showError = savedInstanceState.getBoolean(KEY_IS_ERROR_SHOWING, false)
//            if (showError) motionLayout.transitionToEnd()

            val showBottomSheet = savedInstanceState.getBoolean(KEY_IS_BOTTOM_SHEET_SHOWING, false)
            if (showBottomSheet) showBottomSheet()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        outState.putBoolean(KEY_IS_ERROR_SHOWING, motionLayout.currentState == motionLayout.endState)
        outState.putBoolean(KEY_IS_BOTTOM_SHEET_SHOWING, isBottomSheetShowing)
    }

    private fun showBottomSheet() = BottomSheetDialog(this).apply {
        val bottomSheetBinding = BottomSheetBinding.inflate(layoutInflater)
        setContentView(bottomSheetBinding.root)

        setOnShowListener { isBottomSheetShowing = true }
        setOnDismissListener { isBottomSheetShowing = false }
    }.show()

    /**
     * Listens for the motion layout's next transition to complete and then removes itself and transitions back to the
     * layout's end state.
     */
    private class TryAgain(
        private val showError: () -> Unit
    ): CountDownTimer(300, 300) {
        override fun onFinish() {
            showError()
        }

        override fun onTick(millisUntilFinished: Long) = Unit
    }

    private companion object {
        private const val KEY_IS_ERROR_SHOWING = "is_error_showing"
        private const val KEY_IS_BOTTOM_SHEET_SHOWING = "is_bottom_sheet_showing"
    }
}
