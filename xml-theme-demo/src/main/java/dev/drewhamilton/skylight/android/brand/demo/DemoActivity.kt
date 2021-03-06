package dev.drewhamilton.skylight.android.brand.demo

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.viewbinding.ViewBinding
import com.backbase.deferredresources.DeferredColor
import com.backbase.deferredresources.color.SdkIntDeferredColor
import com.backbase.deferredresources.color.withAlpha
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dev.drewhamilton.skylight.android.brand.demo.databinding.BottomSheetBinding
import dev.drewhamilton.skylight.android.brand.demo.databinding.DemoBinding

class DemoActivity : AppCompatActivity() {

    private val binding: DemoBinding by lazy(mode = LazyThreadSafetyMode.NONE) {
        DemoBinding.inflate(layoutInflater)
    }

    private val scrollingToolbarElevation by lazy(mode = LazyThreadSafetyMode.NONE) {
        resources.getDimension(R.dimen.toolbarElevation)
    }

    private var isFullscreen: Boolean = true

    private var isErrorSnackbarShowing: Boolean = false
    private var isBottomSheetShowing: Boolean = false
    private var isAlertDialogShowing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        val defaultFullscreen = Build.VERSION.SDK_INT >= 29
        setFullscreen(savedInstanceState?.getBoolean(KEY_IS_FULLSCREEN) ?: defaultFullscreen)
        WindowCompat.setDecorFitsSystemWindows(window, !isFullscreen)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            with(windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())) {
                binding.statusBarBackdrop.setHeight(top)
                binding.navigationBarBackrop.setHeight(bottom)
                binding.root.updatePadding(left = left, right = right)

                binding.scrollView.updatePadding(bottom = bottom)
            }

            windowInsets
        }

        binding.setToolbarElevation()
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            binding.setToolbarElevation()
        }

        binding.darkModeSwitch.isChecked = resources.getBoolean(R.bool.nightMode)
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Delay night mode so the switch animation completes:
            afterDelay {
                AppCompatDelegate.setDefaultNightMode(
                    if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }

        binding.fullscreenSwitch.isChecked = isFullscreen
        binding.fullscreenSwitch.setOnCheckedChangeListener { _, isChecked ->
            afterDelay {
                setFullscreen(isChecked)
                recreate()
            }
        }

        binding.buttonsSwitch.setOnCheckedChangeListener { _, isChecked ->
            binding.errorSnackbarButton.isEnabled = isChecked
            binding.bottomSheetButton.isEnabled = isChecked
            binding.alertDialogButton.isEnabled = isChecked
        }

        binding.errorSnackbarButton.setOnClickListener { binding.showErrorSnackbar() }
        binding.bottomSheetButton.setOnClickListener { showBottomSheet() }
        binding.alertDialogButton.setOnClickListener { showAlertDialog() }

        if (savedInstanceState != null) {
            val showError = savedInstanceState.getBoolean(KEY_IS_ERROR_SHOWING, false)
            if (showError) binding.showErrorSnackbar()

            val showBottomSheet = savedInstanceState.getBoolean(KEY_IS_BOTTOM_SHEET_SHOWING, false)
            if (showBottomSheet) showBottomSheet()

            val showAlertDialog = savedInstanceState.getBoolean(KEY_IS_ALERT_DIALOG_SHOWING, false)
            if (showAlertDialog) showAlertDialog()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_IS_FULLSCREEN, isFullscreen)
        outState.putBoolean(KEY_IS_ERROR_SHOWING, isErrorSnackbarShowing)
        outState.putBoolean(KEY_IS_BOTTOM_SHEET_SHOWING, isBottomSheetShowing)
        outState.putBoolean(KEY_IS_ALERT_DIALOG_SHOWING, isAlertDialogShowing)
    }

    private fun setFullscreen(fullscreen: Boolean) {
        isFullscreen = fullscreen
        val theme = if (fullscreen) R.style.Theme_Skylight_Fullscreen else R.style.Theme_Skylight
        setTheme(theme)
    }

    private fun DemoBinding.setToolbarElevation() {
        val scrolledStateRatio = (scrollView.scrollY.toFloat() / scrollView.paddingTop).coerceIn(0f, 1f)
        statusBarBackdrop.elevation = scrolledStateRatio * scrollingToolbarElevation
        toolbar.elevation = scrolledStateRatio * scrollingToolbarElevation
    }

    private fun ViewBinding.showErrorSnackbar() {
        isErrorSnackbarShowing = true
        val snackbar = Snackbar.make(root, "This is a serious error", Snackbar.LENGTH_INDEFINITE)
        with(snackbar) {
            val colorError = DeferredColor.Attribute(R.attr.colorError).resolve(context)
            val colorOnError = DeferredColor.Attribute(R.attr.colorOnError)
            val textOnError = SdkIntDeferredColor(
                minSdk = colorOnError,
                sdk23 = DeferredColor.Resource(R.color.button_text_on_error),
            ).resolveToStateList(context)
            val rippleOnError = SdkIntDeferredColor(
                minSdk = colorOnError.withAlpha(0.12f),
                sdk23 = DeferredColor.Resource(R.color.ripple_on_error),
            ).resolveToStateList(context)

            setBackgroundTint(colorError)
            setTextColor(textOnError)
            setActionTextColor(textOnError)
            view.findViewById<MaterialButton>(R.id.snackbar_action).rippleColor = rippleOnError
            setAction("Dismiss") {
                isErrorSnackbarShowing = false
                snackbar.dismiss()
            }
            show()
        }
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

    private fun View.setHeight(height: Int) {
        layoutParams = layoutParams.apply {
            this.height = height
        }
    }

    private companion object {
        private const val KEY_IS_FULLSCREEN = "is_fullscreen"
        private const val KEY_IS_ERROR_SHOWING = "is_error_showing"
        private const val KEY_IS_BOTTOM_SHEET_SHOWING = "is_bottom_sheet_showing"
        private const val KEY_IS_ALERT_DIALOG_SHOWING = "is_alert_dialog_showing"
    }
}
