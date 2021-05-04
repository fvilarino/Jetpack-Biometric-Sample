package com.francescsoftware.biometricpromptsample

interface PinCallbacks {
    fun onPinUpdated(pin: String)
    fun onPinUnlockRequested()
    fun onBiometricUnlock()
    fun onBackground()
}

val noOpPinCallbacks = object : PinCallbacks {
    override fun onPinUpdated(pin: String) = Unit
    override fun onPinUnlockRequested() = Unit
    override fun onBiometricUnlock() = Unit
    override fun onBackground() = Unit
}
