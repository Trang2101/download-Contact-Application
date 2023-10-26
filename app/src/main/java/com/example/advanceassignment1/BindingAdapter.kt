package com.example.advanceassignment1

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun Visibility(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}
@BindingAdapter("progress")
fun ProgressBar.setProgressValue(progress: Int) {
    this.progress = progress
}