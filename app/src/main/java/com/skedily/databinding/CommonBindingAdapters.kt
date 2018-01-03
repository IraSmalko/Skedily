package com.skedily.databinding


import android.content.res.Resources
import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.skedily.R


@BindingAdapter("loadCircleIcon")
fun loadCircleIcon(imageView: ImageView, iconUrl: String) {
    if (iconUrl.isEmpty()) {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.context, R.drawable.ic_family))
    } else {
        Glide.with(imageView.context).load(iconUrl).apply(bitmapTransform(CircleCrop())).into(imageView)
    }
}

@BindingAdapter("setSpinnerAdapter")
fun setSpinnerAdapter(spinner: Spinner, available: Boolean) {
    val adapter = ArrayAdapter.createFromResource(spinner.context, R.array.planets_array, android.R.layout.simple_spinner_item)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter
}

@BindingAdapter("setItemSelectedListener")
fun setItemSelectedListener(spinner: Spinner, function: Function) {
    spinner.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val selectedItem = parent.getItemAtPosition(position).toString()
            function.invoke(selectedItem)
        }

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }
}

@BindingAdapter("scrollTo")
fun scrollTo(recycler: RecyclerView, position: Int) {
    recycler.layoutManager.scrollToPosition(position)
}

@BindingAdapter("scrollTop")
fun scrollTop(recycler: RecyclerView, position: Int) {
    val lm = recycler.layoutManager as? LinearLayoutManager
    lm?.scrollToPositionWithOffset(position, 0)
}

@BindingAdapter("setHeight")
fun setHeight(layout: ViewGroup, height: Int) {
    layout.layoutParams.height = height

}

interface Function {
    fun invoke(s: String)
}