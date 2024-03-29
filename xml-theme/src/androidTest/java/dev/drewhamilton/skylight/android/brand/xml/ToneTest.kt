package dev.drewhamilton.skylight.android.brand.xml

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.color.calculateTone
import org.junit.Assert.assertEquals
import org.junit.Test

class ToneTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    //region Skylight amber
    @Test fun amber100() {
        assertResourceTone(100, R.color.skylight_amber_100)
    }

    @Test fun amber99() {
        assertResourceTone(99, R.color.skylight_amber_99)
    }

    @Test fun amber95() {
        assertResourceTone(95, R.color.skylight_amber_95)
    }

    @Test fun amber90() {
        assertResourceTone(90, R.color.skylight_amber_90)
    }

    @Test fun amber80() {
        assertResourceTone(80, R.color.skylight_amber_80)
    }

    @Test fun amber70() {
        assertResourceTone(70, R.color.skylight_amber_70)
    }

    @Test fun amber60() {
        assertResourceTone(60, R.color.skylight_amber_60)
    }

    @Test fun amber50() {
        assertResourceTone(50, R.color.skylight_amber_50)
    }

    @Test fun amber40() {
        assertResourceTone(40, R.color.skylight_amber_40)
    }

    @Test fun amber30() {
        assertResourceTone(30, R.color.skylight_amber_30)
    }

    @Test fun amber20() {
        assertResourceTone(20, R.color.skylight_amber_20)
    }

    @Test fun amber10() {
        assertResourceTone(10, R.color.skylight_amber_10)
    }

    @Test fun amber0() {
        assertResourceTone(0, R.color.skylight_amber_0)
    }
    //endregion

    //region Skylight neutral variant rose
    @Test fun neutralRose100() {
        assertResourceTone(100, R.color.skylight_neutralRose_100)
    }

    @Test fun neutralRose99() {
        assertResourceTone(99, R.color.skylight_neutralRose_99)
    }

    @Test fun neutralRose95() {
        assertResourceTone(95, R.color.skylight_neutralRose_95)
    }

    @Test fun neutralRose90() {
        assertResourceTone(90, R.color.skylight_neutralRose_90)
    }

    @Test fun neutralRose80() {
        assertResourceTone(80, R.color.skylight_neutralRose_80)
    }

    @Test fun neutralRose70() {
        assertResourceTone(70, R.color.skylight_neutralRose_70)
    }

    @Test fun neutralRose60() {
        assertResourceTone(60, R.color.skylight_neutralRose_60)
    }

    @Test fun neutralRose50() {
        assertResourceTone(50, R.color.skylight_neutralRose_50)
    }

    @Test fun neutralRose40() {
        assertResourceTone(40, R.color.skylight_neutralRose_40)
    }

    @Test fun neutralRose30() {
        assertResourceTone(30, R.color.skylight_neutralRose_30)
    }

    @Test fun neutralRose20() {
        assertResourceTone(20, R.color.skylight_neutralRose_20)
    }

    @Test fun neutralRose10() {
        assertResourceTone(10, R.color.skylight_neutralRose_10)
    }

    @Test fun neutralRose0() {
        assertResourceTone(0, R.color.skylight_neutralRose_0)
    }
    //endregion

    //region Skylight purple
    @Test fun purple100() {
        assertResourceTone(100, R.color.skylight_purple_100)
    }

    @Test fun purple99() {
        assertResourceTone(99, R.color.skylight_purple_99)
    }

    @Test fun purple95() {
        assertResourceTone(95, R.color.skylight_purple_95)
    }

    @Test fun purple90() {
        assertResourceTone(90, R.color.skylight_purple_90)
    }

    @Test fun purple80() {
        assertResourceTone(80, R.color.skylight_purple_80)
    }

    @Test fun purple70() {
        assertResourceTone(70, R.color.skylight_purple_70)
    }

    @Test fun purple60() {
        assertResourceTone(60, R.color.skylight_purple_60)
    }

    @Test fun purple50() {
        assertResourceTone(50, R.color.skylight_purple_50)
    }

    @Test fun purple40() {
        assertResourceTone(40, R.color.skylight_purple_40)
    }

    @Test fun purple30() {
        assertResourceTone(30, R.color.skylight_purple_30)
    }

    @Test fun purple20() {
        assertResourceTone(20, R.color.skylight_purple_20)
    }

    @Test fun purple10() {
        assertResourceTone(10, R.color.skylight_purple_10)
    }

    @Test fun purple0() {
        assertResourceTone(0, R.color.skylight_purple_0)
    }
    //endregion

    //region Skylight neutral variant purple
    @Test fun neutralPurple100() {
        assertResourceTone(100, R.color.skylight_neutralPurple_100)
    }

    @Test fun neutralPurple99() {
        assertResourceTone(99, R.color.skylight_neutralPurple_99)
    }

    @Test fun neutralPurple95() {
        assertResourceTone(95, R.color.skylight_neutralPurple_95)
    }

    @Test fun neutralPurple90() {
        assertResourceTone(90, R.color.skylight_neutralPurple_90)
    }

    @Test fun neutralPurple80() {
        assertResourceTone(80, R.color.skylight_neutralPurple_80)
    }

    @Test fun neutralPurple70() {
        assertResourceTone(70, R.color.skylight_neutralPurple_70)
    }

    @Test fun neutralPurple60() {
        assertResourceTone(60, R.color.skylight_neutralPurple_60)
    }

    @Test fun neutralPurple50() {
        assertResourceTone(50, R.color.skylight_neutralPurple_50)
    }

    @Test fun neutralPurple40() {
        assertResourceTone(40, R.color.skylight_neutralPurple_40)
    }

    @Test fun neutralPurple30() {
        assertResourceTone(30, R.color.skylight_neutralPurple_30)
    }

    @Test fun neutralPurple20() {
        assertResourceTone(20, R.color.skylight_neutralPurple_20)
    }

    @Test fun neutralPurple10() {
        assertResourceTone(10, R.color.skylight_neutralPurple_10)
    }

    @Test fun neutralPurple0() {
        assertResourceTone(0, R.color.skylight_neutralPurple_0)
    }
    //endregion

    //region Skylight pink
    @Test fun pink100() {
        assertResourceTone(100, R.color.skylight_pink_100)
    }

    @Test fun pink99() {
        assertResourceTone(99, R.color.skylight_pink_99)
    }

    @Test fun pink95() {
        assertResourceTone(95, R.color.skylight_pink_95)
    }

    @Test fun pink90() {
        assertResourceTone(90, R.color.skylight_pink_90)
    }

    @Test fun pink80() {
        assertResourceTone(80, R.color.skylight_pink_80)
    }

    @Test fun pink70() {
        assertResourceTone(70, R.color.skylight_pink_70)
    }

    @Test fun pink60() {
        assertResourceTone(60, R.color.skylight_pink_60)
    }

    @Test fun pink50() {
        assertResourceTone(50, R.color.skylight_pink_50)
    }

    @Test fun pink40() {
        assertResourceTone(40, R.color.skylight_pink_40)
    }

    @Test fun pink30() {
        assertResourceTone(30, R.color.skylight_pink_30)
    }

    @Test fun pink20() {
        assertResourceTone(20, R.color.skylight_pink_20)
    }

    @Test fun pink10() {
        assertResourceTone(10, R.color.skylight_pink_10)
    }

    @Test fun pink0() {
        assertResourceTone(0, R.color.skylight_pink_0)
    }
    //endregion

    //region ref palette primary
    // Sanity check default material color tones
    @Test fun refPrimary100() {
        assertResourceTone(100, R.color.m3_ref_palette_primary100)
    }

    @Test fun refPrimary99() {
        assertResourceTone(99, R.color.m3_ref_palette_primary99)
    }

    @Test fun refPrimary95() {
        assertResourceTone(95, R.color.m3_ref_palette_primary95)
    }

    @Test fun refPrimary90() {
        assertResourceTone(90, R.color.m3_ref_palette_primary90)
    }

    @Test fun refPrimary80() {
        assertResourceTone(80, R.color.m3_ref_palette_primary80)
    }

    @Test fun refPrimary70() {
        assertResourceTone(70, R.color.m3_ref_palette_primary70)
    }

    @Test fun refPrimary60() {
        assertResourceTone(60, R.color.m3_ref_palette_primary60)
    }

    @Test fun refPrimary50() {
        assertResourceTone(50, R.color.m3_ref_palette_primary50)
    }

    @Test fun refPrimary40() {
        assertResourceTone(40, R.color.m3_ref_palette_primary40)
    }

    @Test fun refPrimary30() {
        assertResourceTone(30, R.color.m3_ref_palette_primary30)
    }

    @Test fun refPrimary20() {
        assertResourceTone(20, R.color.m3_ref_palette_primary20)
    }

    @Test fun refPrimary10() {
        assertResourceTone(10, R.color.m3_ref_palette_primary10)
    }

    @Test fun refPrimary0() {
        assertResourceTone(0, R.color.m3_ref_palette_primary0)
    }
    //endregion

    private fun assertResourceTone(expected: Int, @ColorRes res: Int) {
        assertResourceTone(expected.toFloat(), res)
    }

    private fun assertResourceTone(expected: Float, @ColorRes res: Int) {
        assertEquals(expected, calculateResourceTone(res), 0.6f)
    }

    private fun calculateResourceTone(@ColorRes res: Int): Float {
        return calculateTone(ContextCompat.getColor(context, res))
    }
}
