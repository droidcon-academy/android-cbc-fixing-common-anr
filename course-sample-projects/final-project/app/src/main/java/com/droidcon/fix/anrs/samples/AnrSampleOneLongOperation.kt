package com.droidcon.fix.anrs.samples

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Causes an ANR by blocking the main thread for 10 seconds.
 */
fun anrByOneLongOperation() {
    DownloadManager.downloadBinaryResources {
        // use the binary resource here...
    }
}

/**
 * A fake download manager that simulates downloading binary resources.
 */
private object DownloadManager {
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()

    /**
     * Simulates downloading binary resources on the main thread with a 10s delay.
     */
    fun downloadBinaryResources(callback: (ByteArray) -> Unit) {
        executor.submit {
            Thread.sleep(10000)
            callback(ByteArray(0))
        }
    }
}
