package dev.drewhamilton.skylight.android.brand.demo

import android.content.Context
import android.graphics.Color
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * Fixes a bug in [BottomSheetDialog] where it does not respect
 * [android.R.attr.windowLightNavigationBar]'s value in the dialog theme.
 */
class FixedBottomSheetDialog(context: Context) : BottomSheetDialog(context) {

    @Suppress("DEPRECATION") // Copying superclass logic
    override fun onAttachedToWindow() {
        val window = window
        val initialSystemUiVisibility = window?.decorView?.systemUiVisibility ?: 0

        super.onAttachedToWindow()

        if (window != null) {
            // If the navigation bar is translucent at all, the BottomSheet should be edge to edge
            val drawEdgeToEdge = edgeToEdgeEnabled && Color.alpha(window.navigationBarColor) < 0xFF
            if (drawEdgeToEdge) {
                // Copied from super.onAttachedToWindow:
                val edgeToEdgeFlags =
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

                // Fix super-class's window flag bug by respecting the intial system UI visibility:
                window.decorView.systemUiVisibility = edgeToEdgeFlags or initialSystemUiVisibility
            }
        }
    }
}
