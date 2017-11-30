package com.skedily.databinding


import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.google.android.gms.common.SignInButton
import com.skedily.R


@BindingAdapter("loadCircleIcon")
fun loadCircleIcon(imageView: ImageView, iconId: String) {
    if (iconId.isEmpty()) {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.ic_family))
    } else {
        Glide.with(imageView.context).load(iconId).apply(bitmapTransform(CircleCrop())).into(imageView)
    }
}

@BindingAdapter("setSpinnerAdapter")
fun setSpinnerAdapter(spinner: Spinner, available: Boolean) {
    val adapter = ArrayAdapter.createFromResource(spinner.context, R.array.planets_array, android.R.layout.simple_spinner_item)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter
}
