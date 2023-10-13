package com.droidcon.fix.anrs.samples

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * Wait on another thread for 10 seconds.
 */
fun anrByWaitingOnBgThread() {
    performWork {
        // display image in UI here...
    }
}

private fun performWork(callback: (Any) -> Unit) {
    Executors.newSingleThreadExecutor().submit {
        val client = NetworkApiClient()
        val imageFuture = client.downloadImage()
        val image = imageFuture.get()
        callback(image)
    }
}

private class NetworkApiClient {
    private val backgroundExecutor = Executors.newSingleThreadExecutor()

    fun downloadImage(): Future<*> {
        return backgroundExecutor.submit(Callable {
            Thread.sleep(10000) // simulate network requests
            ByteArray(0)
        })
    }
}
