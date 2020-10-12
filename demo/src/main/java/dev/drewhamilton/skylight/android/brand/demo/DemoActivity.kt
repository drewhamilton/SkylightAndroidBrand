package dev.drewhamilton.skylight.android.brand.demo

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.drewhamilton.skylight.android.brand.demo.databinding.BottomSheetBinding
import dev.drewhamilton.skylight.android.brand.demo.databinding.DemoBinding

class DemoActivity : AppCompatActivity() {

    private val binding: DemoBinding by lazy(mode = LazyThreadSafetyMode.NONE) {
        DemoBinding.inflate(layoutInflater)
    }

    private var isBottomSheetShowing: Boolean = false
    private var isAlertDialogShowing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonsSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.errorBannerButton.isEnabled = isChecked
            binding.bottomSheetButton.isEnabled = isChecked
            binding.alertDialogButton.isEnabled = isChecked
        }

        binding.errorBanner.setPrimaryButtonOnClickListener {
            TryAgain { binding.root.transitionToEnd() }.start()
            binding.root.transitionToStart()
        }
        binding.errorBanner.setSecondaryButtonOnClickListener { binding.root.transitionToStart() }
        binding.errorBannerButton.setOnClickListener { binding.root.transitionToEnd() }

        binding.bottomSheetButton.setOnClickListener { showBottomSheet() }

        binding.alertDialogButton.setOnClickListener { showAlertDialog() }

        if (savedInstanceState != null) {
            val showError = savedInstanceState.getBoolean(KEY_IS_ERROR_SHOWING, false)
            if (showError) binding.root.transitionToEnd()

            val showBottomSheet = savedInstanceState.getBoolean(KEY_IS_BOTTOM_SHEET_SHOWING, false)
            if (showBottomSheet) showBottomSheet()

            val showAlertDialog = savedInstanceState.getBoolean(KEY_IS_ALERT_DIALOG_SHOWING, false)
            if (showAlertDialog) showAlertDialog()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_ERROR_SHOWING, binding.root.currentState == binding.root.endState)
        outState.putBoolean(KEY_IS_BOTTOM_SHEET_SHOWING, isBottomSheetShowing)
        outState.putBoolean(KEY_IS_ALERT_DIALOG_SHOWING, isAlertDialogShowing)
    }

    private fun showBottomSheet() = BottomSheetDialog(this).apply {
        val bottomSheetBinding = BottomSheetBinding.inflate(layoutInflater)
        setContentView(bottomSheetBinding.root)

        setOnShowListener { isBottomSheetShowing = true }
        setOnDismissListener { isBottomSheetShowing = false }
    }.show()

    private fun showAlertDialog() {
        isAlertDialogShowing = true
        MaterialAlertDialogBuilder(this)
            .setTitle("Alert dialog")
            .setMessage("This is what an alert dialog looks like.")
            .setPositiveButton("Neat") { _, _ -> }
            .setOnDismissListener { isAlertDialogShowing = false }
            .show()
    }

    /**
     * Listens for the motion layout's next transition to complete and then removes itself and transitions back to the
     * layout's end state.
     */
    private class TryAgain(
        private val showError: () -> Unit
    ): CountDownTimer(300, 300) {
        override fun onFinish() = showError()
        override fun onTick(millisUntilFinished: Long) = Unit
    }

    private companion object {
        private const val KEY_IS_ERROR_SHOWING = "is_error_showing"
        private const val KEY_IS_BOTTOM_SHEET_SHOWING = "is_bottom_sheet_showing"
        private const val KEY_IS_ALERT_DIALOG_SHOWING = "is_alert_dialog_showing"
    }
}
