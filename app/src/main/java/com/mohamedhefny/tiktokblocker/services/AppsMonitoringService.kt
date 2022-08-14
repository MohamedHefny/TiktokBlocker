package com.mohamedhefny.tiktokblocker.services

import android.accessibilityservice.AccessibilityService
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent

class AppsMonitoringService : AccessibilityService() {

    companion object {
        private const val TAG = "ActiveAppServiceTAG"
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d(TAG, "Accessibility service connected")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d(TAG, "Accessibility event $event")
    }

    override fun onKeyEvent(event: KeyEvent?): Boolean {
        Log.d(TAG, "Accessibility key event $event")
        return super.onKeyEvent(event)
    }

    override fun onInterrupt() {
        Log.d(TAG, "Accessibility service interrupted")
    }

}