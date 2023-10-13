package com.droidcon.fix.anrs.samples.components

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.concurrent.Executors

internal class AnrBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        performLongOperation()
    }

    private fun performLongOperation() {
        val pendingResult = goAsync()

        Executors.newSingleThreadExecutor().submit {
            Thread.sleep(12000)
            pendingResult.finish()
        }
    }
}
