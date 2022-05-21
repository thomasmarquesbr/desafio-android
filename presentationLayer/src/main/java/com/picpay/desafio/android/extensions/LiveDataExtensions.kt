package com.picpay.desafio.android.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.thomas.archtecture_framework.wrapper.Event

inline fun <T> LiveData<Event<T>>.observeEvent(
        owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit,
) {
    observe(owner) { it.getContentIfNotHandled()?.let(onEventUnhandledContent) }
}
