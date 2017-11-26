package com.skedily.databinding

import android.databinding.BindingAdapter
import android.net.Uri
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.skedily.R
import jp.wasabeef.glide.transformations.BlurTransformation

@BindingAdapter("loadCircleIcon")
fun loadCircleIcon(imageView: ImageView, iconId: String) {
    if (iconId.isEmpty()) {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.ic_family))
    } else {
        Glide.with(imageView.context).load(iconId).apply(bitmapTransform(CircleCrop())).into(imageView)
    }
}

@BindingAdapter("bottomSheetCallback")
fun setBottomSheetCallback(view: View, callback: BottomSheetBehavior.BottomSheetCallback?) {
    val behavior = BottomSheetBehavior.from(view)
    behavior.setBottomSheetCallback(callback)
}
