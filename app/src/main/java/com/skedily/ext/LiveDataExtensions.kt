package com.skedily.ext

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Flowable<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this)
fun <T> Maybe<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this.toFlowable())
fun <T> Single<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this.toFlowable())

fun <T> Observable<T>.asLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this.toFlowable(io.reactivex.BackpressureStrategy.BUFFER))
