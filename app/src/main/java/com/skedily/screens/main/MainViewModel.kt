package com.skedily.screens.main

import android.databinding.Bindable
import android.support.annotation.IdRes
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.utils.weak

/**
 * Created by smalk on 11/26/2017.
 */
enum class State(@IdRes val menuId: Int) {
    DASHBOARD(R.id.dashboard),
    SCHEDULE(R.id.schedule),
    FAMILY(R.id.family)
}

class MainViewModel : BaseViewModel() {

    var interactor by weak<MainInteractor>()

    var state: State? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.state)
            value?.let { interactor?.display(value) }
        }

    var refreshTintRadioButton: Boolean = false
        @Bindable("state") get
        private set(value) {
            field = value
            notifyPropertyChanged(BR.refreshTintRadioButton)
        }
}