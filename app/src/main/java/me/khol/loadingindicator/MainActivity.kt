package me.khol.loadingindicator

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getDrawableOrThrow
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawable = getProgressBarDrawable()
        (drawable as? Animatable)?.start()

        val states = ColorStateList(arrayOf(intArrayOf()), intArrayOf(fetchPrimaryColor()))
        loadingIndicatorLayout.setEndIconTintList(states)
        drawable.setTintList(states)

        loadingIndicatorLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        loadingIndicatorLayout.endIconDrawable = drawable
    }
}

fun Context.getProgressBarDrawable(): Drawable {
    val value = TypedValue()
    theme.resolveAttribute(android.R.attr.progressBarStyleSmall, value, false)
    val progressBarStyle = value.data
    val attributes = intArrayOf(android.R.attr.indeterminateDrawable)
    val array = obtainStyledAttributes(progressBarStyle, attributes)
    val drawable = array.getDrawableOrThrow(0)
    array.recycle()
    return drawable
}

fun Context.fetchPrimaryColor(): Int {
    val array = obtainStyledAttributes(intArrayOf(android.R.attr.colorPrimary))
    val color = array.getColorOrThrow(0)
    array.recycle()
    return color
}
