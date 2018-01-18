package com.skedily.databinding

import android.databinding.BindingAdapter
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView

import java.util.*

/**
 * Created by smalk on 1/18/2018.
 */
class ScrollListener(private val fab: FloatingActionButton) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {
            // Scroll Down
            if (fab.isShown()) {
                fab.hide();
            }
        } else if (dy < 0) {
            // Scroll Up
            if (!fab.isShown()) {
                fab.show();
            }
        }
    }
}

private val listeners = WeakHashMap<RecyclerView, ScrollListener>()

@BindingAdapter("addHidingFabListener")
fun addHidingFabListener(rV: RecyclerView, fab: FloatingActionButton) {
        listeners[rV] = ScrollListener(fab)
        listeners[rV]?.let { rV.addOnScrollListener(it) }
}
