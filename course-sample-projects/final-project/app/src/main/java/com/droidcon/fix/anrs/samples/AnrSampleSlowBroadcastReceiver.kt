package com.droidcon.fix.anrs.samples

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.droidcon.fix.anrs.samples.components.AnrBroadcastReceiver

private const val ACTION_BLOCK_ON_RECEIVE = "com.droidcon.ACTION_BLOCK_ON_RECEIVE"

/**
 * Trigger an ANR via a slow broadcast receiver
 */
fun anrBySlowBroadcastReceiver(context: Context) {
    setupBroadcastReceiver(context)
    val intent = Intent(ACTION_BLOCK_ON_RECEIVE)
    context.sendBroadcast(intent)
}

private fun setupBroadcastReceiver(context: Context) {
    val receiver = AnrBroadcastReceiver()
    val filter = IntentFilter(ACTION_BLOCK_ON_RECEIVE)
    context.registerReceiver(receiver, filter)
}
