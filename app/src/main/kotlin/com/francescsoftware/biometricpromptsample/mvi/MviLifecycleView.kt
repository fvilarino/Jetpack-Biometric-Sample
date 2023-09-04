package com.francescsoftware.biometricpromptsample.mvi

import androidx.lifecycle.LifecycleOwner

interface MviLifecycleView<S : State, E : Event> : MviView<S, E>, LifecycleOwner
