package com.francescsoftware.biometricpromptsample.mvi;

import androidx.lifecycle.LifecycleOwner

interface MviView<S : State, E : Event> : LifecycleOwner {
    fun onState(state: S)
    fun onEvent(event: E) = Unit
}
