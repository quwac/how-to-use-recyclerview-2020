package io.github.quwac.how_to_use_recyclerview_2020.ui.main

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Event<T> {
    val value: T
}

private data class EventImpl<T>(override val value: T) : Event<T> {
    var consumed = false
}

typealias LiveDataEvent<T> = LiveData<Event<T>>
typealias MutableLiveDataEvent<T> = MutableLiveData<Event<T>>

fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: Observer<in T>) {
    observe(owner, {
        check(it is EventImpl)
        if (!it.consumed) {
            it.consumed = true
            observer.onChanged(it.value)
        }
    })
}

@MainThread
fun <T> MutableLiveData<Event<T>>.setValue(value: T) {
    this.value = EventImpl(value)
}

fun <T> MutableLiveData<Event<T>>.postValue(value: T) {
    this.postValue(EventImpl(value))
}