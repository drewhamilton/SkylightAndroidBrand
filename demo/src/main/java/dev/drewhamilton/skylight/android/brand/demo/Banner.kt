package dev.drewhamilton.skylight.android.brand.demo

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.google.android.material.button.MaterialButton
import dev.drewhamilton.skylight.android.brand.demo.databinding.BannerBinding

class Banner : ConstraintLayout {

    private val binding: BannerBinding = BannerBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributeSet(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributeSet(attrs)
    }

    private fun initAttributeSet(attrs: AttributeSet) {
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.Banner)
        try {
            val messageTextColor = styledAttributes.getColorStateList(R.styleable.Banner_bannerMessageTextColor)
            if (messageTextColor != null)
                binding.bannerMessageView.setTextColor(messageTextColor)

            val buttonTextColor = styledAttributes.getColorStateList(R.styleable.Banner_bannerButtonTextColor)
            if (buttonTextColor != null) {
                binding.primaryButton.setTextColor(buttonTextColor)
                binding.secondaryButton.setTextColor(buttonTextColor)
            }

            val buttonRippleColor = styledAttributes.getColorStateList(R.styleable.Banner_bannerButtonRippleColor)
            if (buttonRippleColor != null) {
                binding.primaryButton.rippleColor = buttonRippleColor
                binding.secondaryButton.rippleColor = buttonRippleColor
            }

            binding.bannerMessageView.text = styledAttributes.getString(R.styleable.Banner_bannerMessage)
            binding.primaryButton.text = styledAttributes.getString(R.styleable.Banner_bannerPrimaryButtonText)
            binding.secondaryButton.text = styledAttributes.getString(R.styleable.Banner_bannerSecondaryButtonText)
        } finally {
            styledAttributes.recycle()
        }
    }

    var bannerMessage: CharSequence
        get() = binding.bannerMessageView.text
        set(value) {
            binding.bannerMessageView.text = value
        }

    var primaryButtonText: CharSequence
        get() = binding.primaryButton.text
        set(value) {
            binding.primaryButton.text = value
        }

    var secondaryButtonText: CharSequence
        get() = binding.secondaryButton.text
        set(value) {
            binding.secondaryButton.text = value
        }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        binding.primaryButton.isEnabled = enabled
        binding.secondaryButton.isEnabled = enabled
    }

    fun setBannerMessage(@StringRes resId: Int) = binding.bannerMessageView.setText(resId)

    fun setPrimaryButtonText(@StringRes resId: Int) = binding.primaryButton.setText(resId)

    fun setSecondaryButtonText(@StringRes resId: Int) = binding.secondaryButton.setText(resId)

    fun setPrimaryButtonOnClickListener(listener: OnClickListener) {
        binding.primaryButton.setOnClickListener(listener)
    }

    fun setSecondaryButtonOnClickListener(listener: OnClickListener) {
        binding.secondaryButton.setOnClickListener(listener)
    }
}
