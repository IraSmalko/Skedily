package com.skedily.utils

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View


class ScrollAwareFABBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, directTargetChild: View, target: View, axes: Int): Boolean {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes)
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        if (dyConsumed > 0 && child.getVisibility() === View.VISIBLE) {
            child.hide()
        } else if (dyConsumed < 0 && child.getVisibility() !== View.VISIBLE) {
            child.show()
        }
    }
}
