package com.skedily.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import com.skedily.BR

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Maybe
import io.reactivex.subjects.PublishSubject
import kotlin.reflect.KClass

abstract class BaseActivity(
        private val layoutId: Int? = null
) : RxAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutId?.let { setContentLayout(it) }
    }
    private val activityResults = PublishSubject.create<ActivityResult>()

    protected open fun setContentLayout(@LayoutRes layoutId: Int) {
        setContentView(layoutId)
    }

    fun Fragment.replaceAndCommit(@IdRes containerId: Int, addToBackStack: Boolean = false) {
        var transaction = supportFragmentManager.beginTransaction()
                .replace(containerId, this)

        if (addToBackStack)
            transaction = transaction.addToBackStack(null)

        if (addToBackStack)
            transaction.commit()
        else
            transaction.commitNow()
    }

    final override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityResults.onNext(ActivityResult(requestCode, resultCode, data))
    }

    protected fun awaitActivityResult(requestCode: Int): Maybe<ActivityResult> = activityResults.filter { it.requestCode == requestCode }.firstElement()
}

data class ActivityResult(val requestCode: Int, val resultCode: Int, val data: Intent?)

abstract class BaseBoundActivity<out TBinding : ViewDataBinding>(
        layoutId: Int,
        private val disableTransitions: Boolean = false
) : BaseActivity(layoutId) {
    private lateinit var innerBinding: TBinding
    protected val binding: TBinding by lazy { innerBinding }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (disableTransitions) overridePendingTransition(0, 0)
    }

    override fun setContentLayout(layoutId: Int) {
        innerBinding = DataBindingUtil.setContentView(this, layoutId)
    }
}

abstract class BaseBoundVmActivity<out TBinding : ViewDataBinding, out TViewModel : ViewModel>(
        layoutId: Int,
        private val vmClass: KClass<TViewModel>,
        private val autoBindVm: Boolean = true,
        disableTransitions: Boolean = false
) : BaseBoundActivity<TBinding>(layoutId, disableTransitions) {
    protected val vm: TViewModel by lazy { ViewModelProviders.of(this).get(vmClass.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (autoBindVm) binding.setVariable(BR.vm, vm)
    }
}
