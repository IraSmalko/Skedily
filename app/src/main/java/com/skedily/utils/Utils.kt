package com.skedily.utils


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by smalk on 11/26/2017.
 */
fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, frameId: Int) {
    val transaction = manager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}