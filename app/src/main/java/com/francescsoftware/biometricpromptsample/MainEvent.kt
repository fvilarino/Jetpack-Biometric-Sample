package com.francescsoftware.biometricpromptsample

import com.francescsoftware.biometricpromptsample.mvi.Event
import com.francescsoftware.biometricpromptsample.mvi.MviIntent
import com.francescsoftware.biometricpromptsample.mvi.ReduceAction
import com.francescsoftware.biometricpromptsample.mvi.State

enum class LoadState {
    SHOW_PIN,
    SHOW_CONTENT,
}

data class MainState(
    val loadState: LoadState,
    val pin: String,
    val pinButtonEnabled: Boolean,
    val pinError: Boolean,
) : State

sealed class MainEvent : Event

sealed class MainMviIntent : MviIntent {
    data class PinUpdated(val pin: String) : MainMviIntent()
    object PinUnlockRequested : MainMviIntent()
    object BiometricUnlock : MainMviIntent()
    object OnBackground : MainMviIntent()
}

sealed class MainReduceAction : ReduceAction {
    data class Loaded(val showPin: Boolean) : MainReduceAction()
    data class PinUpdated(val pin: String, val buttonEnabled: Boolean) : MainReduceAction()
    object Lock : MainReduceAction()
    object Unlock : MainReduceAction()
    object PinError : MainReduceAction()
    object OnShowPin : MainReduceAction()
}
