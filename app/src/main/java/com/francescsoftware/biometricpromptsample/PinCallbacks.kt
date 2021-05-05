package com.francescsoftware.biometricpromptsample

interface PinCallbacks {
    fun onPinChange(pin: String)
    fun onPinUnlockClick()
}

val noOpPinCallbacks = object : PinCallbacks {
    override fun onPinChange(pin: String) = Unit
    override fun onPinUnlockClick() = Unit
}
