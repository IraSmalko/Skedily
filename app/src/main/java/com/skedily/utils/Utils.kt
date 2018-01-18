package com.skedily.utils


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by smalk on 11/26/2017.
 */
fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, containerId: Int) {
    val transaction = manager.beginTransaction()
    transaction.add(containerId, fragment)
    transaction.commit()
}

fun replaceFragment(manager: FragmentManager, fragment: Fragment, containerId: Int) {
    val transaction = manager.beginTransaction()
    transaction.replace(containerId, fragment)
    transaction.addToBackStack(fragment.javaClass.canonicalName)
    transaction.commit()
}

