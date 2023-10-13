package com.droidcon.fix.anrs.samples


/**
 * Causes an ANR by blocking the main thread for 10 seconds.
 */
fun anrByOneLongOperation() {
    DownloadManager.downloadBinaryResources()
}

/**
 * A fake download manager that simulates downloading binary resources.
 */
private object DownloadManager {

    /**
     * Simulates downloading binary resources on the main thread with a 10s delay.
     */
    fun downloadBinaryResources(): ByteArray {
        Thread.sleep(10000)
        return ByteArray(0)
    }
}
