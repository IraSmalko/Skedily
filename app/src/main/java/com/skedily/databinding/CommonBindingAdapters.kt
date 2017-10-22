package com.skedily.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("loadIcon")
fun loadIcon(imageView: ImageView, iconId: String) {
    Glide.with(imageView.context).load(iconId).into(imageView)
}
