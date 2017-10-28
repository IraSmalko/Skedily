package com.skedily.databinding

import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.skedily.R
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("loadCircleIcon")
fun loadCircleIcon(imageView: ImageView, iconId: String) {
     Glide.with(imageView.context).load(iconId).apply(bitmapTransform(CircleCrop())).into(imageView)
}
