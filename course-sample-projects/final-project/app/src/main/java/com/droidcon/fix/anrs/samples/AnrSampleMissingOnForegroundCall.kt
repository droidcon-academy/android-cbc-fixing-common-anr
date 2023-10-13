package com.droidcon.fix.anrs.samples

import android.content.Context
import android.os.Build
import com.droidcon.fix.anrs.samples.components.AnrForegroundService

/**
 * Forget to call onForeground in a Foreground service
 */
fun anrByMissingOnForegroundCall(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        AnrForegroundService.startAnrService(context)
    } else {
        throw UnsupportedOperationException("This ANR example only works on API 26+")
    }
}
