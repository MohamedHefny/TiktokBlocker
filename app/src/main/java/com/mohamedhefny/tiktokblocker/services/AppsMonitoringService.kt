package com.mohamedhefny.tiktokblocker.services

import android.accessibilityservice.AccessibilityService
import android.app.ActivityManager
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import com.mohamedhefny.tiktokblocker.MainActivity
import com.mohamedhefny.tiktokblocker.R

class AppsMonitoringService : AccessibilityService() {

    companion object {
        private const val TAG = "ActiveAppServiceTAG"
    }

    private lateinit var activityManager: ActivityManager
    private lateinit var tiktokPackageName: String

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.d(TAG, "Accessibility service connected")
        activityManager = applicationContext.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        tiktokPackageName = applicationContext.getString(R.string.tiktok_package_name)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.d(TAG, "Accessibility event $event")

        if (event?.packageName != tiktokPackageName)
            return

        val isBackPerformed = when (event.className) {
            "android.widget.FrameLayout" ->
                performGlobalAction(GLOBAL_ACTION_BACK)
            "com.ss.android.ugc.aweme.main.MainActivity" ->
                performGlobalAction(GLOBAL_ACTION_BACK) && performGlobalAction(GLOBAL_ACTION_BACK)
            else -> false
        }

        if (isBackPerformed) {
            Intent(this, MainActivity::class.java)
                .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
                .also { startActivity(it) }
        }
    }

    override fun onKeyEvent(event: KeyEvent?): Boolean {
        Log.d(TAG, "Accessibility key event $event")
        return super.onKeyEvent(event)
    }

    override fun onInterrupt() {
        Log.d(TAG, "Accessibility service interrupted")
    }

}