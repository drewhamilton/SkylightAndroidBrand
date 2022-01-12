package com.google.android.material.color

import androidx.annotation.ColorInt
import org.jetbrains.annotations.TestOnly

/**
 * Calculates the Material 3 "tone" of the given [argb] color.
 *
 * Located in the material color package because it uses package-private (i.e. internal) APIs from
 * Material Components; may be unstable.
 */
@TestOnly
internal fun calculateTone(@ColorInt argb: Int): Float = Hct.fromInt(argb).tone
