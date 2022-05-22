package com.thomas.archtecture_framework.state

open class State<DATA>(
        open val data: DATA? = null,
) {
    open class Success<DATA> : State<DATA>()
}
