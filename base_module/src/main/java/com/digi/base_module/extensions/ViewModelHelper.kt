package com.digi.base_module.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T : Any, L : MutableLiveData<T>> LifecycleOwner.observeLiveData(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer(body))
}

inline fun <T> LiveData<T>.observeOnce(
    lifecycleOwner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) {
    observe(
        lifecycleOwner,
        object : Observer<T> {
            override fun onChanged(t: T) {
                observer.invoke(t)
                removeObserver(this)
            }
        }
    )
}
