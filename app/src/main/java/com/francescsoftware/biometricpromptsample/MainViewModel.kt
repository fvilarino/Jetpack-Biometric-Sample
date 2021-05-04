package com.francescsoftware.biometricpromptsample

import androidx.lifecycle.DefaultLifecycleObserver
import com.francescsoftware.biometricpromptsample.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val MinPinLength = 4
private const val MaxPinLength = 16
private const val CorrectPin = "1234"

@HiltViewModel
class MainViewModel @Inject constructor(
) : MviViewModel<MainState, MainEvent, MainMviIntent, MainReduceAction>(
    MainState(
        loadState = LoadState.SHOW_PIN,
        pin = "",
        pinButtonEnabled = false,
        pinError = false,
    )
), PinCallbacks, DefaultLifecycleObserver {

    private val unlockPin = CorrectPin

    override suspend fun executeIntent(intent: MainMviIntent) {
        when (intent) {
            is MainMviIntent.PinUpdated -> {
                if (intent.pin.length <= MaxPinLength)
                    handle(
                        MainReduceAction.PinUpdated(
                            pin = intent.pin,
                            buttonEnabled = intent.pin.length >= MinPinLength,
                        )
                    )
            }
            MainMviIntent.PinUnlockRequested -> if (currentState.pin == unlockPin) {
                handle(MainReduceAction.Unlock)
            } else {
                handle(MainReduceAction.PinError)
            }
            MainMviIntent.BiometricUnlock -> handle(MainReduceAction.Unlock)
            MainMviIntent.OnBackground -> handle(MainReduceAction.Lock)
        }
    }

    override fun reduce(state: MainState, reduceAction: MainReduceAction): MainState =
        when (reduceAction) {
            is MainReduceAction.Loaded -> state.copy(
                loadState = if (reduceAction.showPin) {
                    LoadState.SHOW_PIN
                } else {
                    LoadState.SHOW_CONTENT
                }
            )
            is MainReduceAction.PinUpdated -> state.copy(
                pin = reduceAction.pin,
                pinButtonEnabled = reduceAction.buttonEnabled,
                pinError = false,
            )
            MainReduceAction.PinError -> state.copy(pinError = true)
            MainReduceAction.Unlock -> state.copy(
                loadState = LoadState.SHOW_CONTENT,
                pin = "",
                pinError = false,
            )
            MainReduceAction.Lock -> state.copy(
                loadState = LoadState.SHOW_PIN,
            )
            MainReduceAction.OnShowPin -> state.copy(
                loadState = LoadState.SHOW_PIN,
                pin = "",
                pinButtonEnabled = false,
                pinError = false,
            )
        }

    override fun onPinUpdated(pin: String) = onIntent(MainMviIntent.PinUpdated(pin))

    override fun onPinUnlockRequested() = onIntent(MainMviIntent.PinUnlockRequested)

    override fun onBiometricUnlock() = onIntent(MainMviIntent.BiometricUnlock)

    override fun onBackground() = onIntent(MainMviIntent.OnBackground)
}
