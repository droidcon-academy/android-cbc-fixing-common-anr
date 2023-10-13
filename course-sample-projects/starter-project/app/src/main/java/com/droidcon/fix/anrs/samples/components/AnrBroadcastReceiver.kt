package com.droidcon.fix.anrs.samples.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

internal class AnrBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        performLongOperation()
    }

    private fun performLongOperation() {
        Thread.sleep(12000)
    }
}
