package dev.drewhamilton.skylight.android.brand.demo

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.motion.widget.MotionLayout
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
        if (savedInstanceState == null) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.darkModeSwitch.isChecked = resources.getBoolean(R.bool.nightMode)
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Delay night mode so the switch animation completes:
            afterDelay {
                AppCompatDelegate.setDefaultNightMode(
                    if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }

        binding.buttonsSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.errorBannerButton.isEnabled = isChecked
            binding.bottomSheetButton.isEnabled = isChecked
            binding.alertDialogButton.isEnabled = isChecked
        }

        binding.root.addTransitionListener(ErrorBannerTransitionListener(binding.errorBanner))
        binding.errorBanner.setPrimaryButtonOnClickListener {
            afterDelay { binding.root.transitionToEnd() }
            binding.root.transitionToStart()
        }
        binding.errorBanner.setSecondaryButtonOnClickListener { binding.root.transitionToStart() }
        binding.errorBanner.isEnabled = false

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
     * Executes [block] after [millis].
     */
    private inline fun afterDelay(millis: Long = 400, crossinline block: () -> Unit) {
        object : CountDownTimer(millis, millis) {
            override fun onFinish() = block()
            override fun onTick(millisUntilFinished: Long) = Unit
        }.start()
    }

    private class ErrorBannerTransitionListener(
        private val errorBanner: Banner
    ) : MotionLayout.TransitionListener {
        override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) = Unit

        override fun onTransitionChange(
            motionLayout: MotionLayout,
            startId: Int, endId: Int,
            progress: Float
        ) = Unit

        override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
            errorBanner.isEnabled = currentId == R.id.errorBannerShowing
        }

        override fun onTransitionTrigger(
            motionLayout: MotionLayout,
            triggerId: Int,
            positive: Boolean, progress: Float
        ) = Unit
    }

    private companion object {
        private const val KEY_IS_ERROR_SHOWING = "is_error_showing"
        private const val KEY_IS_BOTTOM_SHEET_SHOWING = "is_bottom_sheet_showing"
        private const val KEY_IS_ALERT_DIALOG_SHOWING = "is_alert_dialog_showing"

        private const val ANIMATION_DURATION = 350L
    }
}
