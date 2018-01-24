package com.skedily.databinding


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

@BindingAdapter("setUserDots")
fun setUserDots(v: TextView, userList: List<User>) {
    when (userList.size) {
        1 -> {
            val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle)
            indicator?.let {
                it.setTint(userList.first().color)
                it.setBounds(0, 2, 0, 0)
            }
            v.setCompoundDrawables(null, indicator, null, null)
        }
        2 -> {
            val firstIndicator = ContextCompat.getDrawable(v.context, R.drawable.circle)
            firstIndicator?.setTint(userList.first().color)
            val secondIndicator = ContextCompat.getDrawable(v.context, R.drawable.circle)
            secondIndicator?.setTint(userList[1].color)
            v.setCompoundDrawables(firstIndicator, null, secondIndicator, null)
        }
        3 -> {
            val firstIndicator = ContextCompat.getDrawable(v.context, R.drawable.circle)
            firstIndicator?.setTint(userList.first().color)
            val secondIndicator = ContextCompat.getDrawable(v.context, R.drawable.circle)
            secondIndicator?.let {
                it.setTint(userList[0].color)
                it.setBounds(0, 2, 0, 0)
            }
            val thirdIndicator = ContextCompat.getDrawable(v.context, R.drawable.circle)
            thirdIndicator?.setTint(userList[2].color)
            v.setCompoundDrawables(firstIndicator, secondIndicator, thirdIndicator, null)
        }
        else -> {
            val indicator = ContextCompat.getDrawable(v.context, R.drawable.circle)
            indicator?.let {
                it.setTint(ContextCompat.getColor(v.context, R.color.darkGreey))
                it.setBounds(0, 2, 0, 0)
            }
            v.setCompoundDrawables(null, indicator, null, null)
        }
    }

}

interface Function {
    fun invoke(s: String)
}