package com.skedily.databinding


import android.databinding.BindingAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.skedily.R
import com.skedily.model.User


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

@BindingAdapter("setUserDots", "numberOfDot")
fun setUserDots(v: View, userList: List<User>, numberOfDot: Int) {
    when (userList.size) {
        1 -> {
            if (numberOfDot == 2) {
                val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle_indicator)
                indicator?.setTint(userList.first().color)
                v.background = indicator
            }
        }
        2 -> {
            if (numberOfDot == 1) {
                val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle_indicator)
                indicator?.setTint(userList.first().color)
                v.background = indicator
            }
            if (numberOfDot == 2) {
                v.visibility = View.GONE
            }
            if (numberOfDot == 3) {
                val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle_indicator)
                indicator?.setTint(userList[1].color)
                v.background = indicator
            }
        }
        3 -> {
            if (numberOfDot == 1) {
                val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle_indicator)
                indicator?.setTint(userList.first().color)
                v.background = indicator
            }
            if (numberOfDot == 2) {
                val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle_indicator)
                indicator?.setTint(userList[1].color)
                v.background = indicator
            }
            if (numberOfDot == 3) {
                val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle_indicator)
                indicator?.setTint(userList[2].color)
                v.background = indicator
            }
        }
        else -> {
            if (userList.size > 3 && numberOfDot == 2) {
                val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle_indicator)
                indicator?.setTint(ContextCompat.getColor(v.context, R.color.darkGrey))
                v.background = indicator
            }
        }
    }
}

interface Function {
    fun invoke(s: String)
}
