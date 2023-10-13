package com.droidcon.fix.anrs.samples

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock


/**
 * Cause a deadlock where Thread A is waiting for Thread B to complete
 * (and vice versa).
 */
fun anrByThreadDeadlock() {
    DeadlockExample().triggerDeadlock()
}

private class DeadlockExample {

    private val backgroundExecutor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())
    private val lock = ReentrantLock()

    fun triggerDeadlock() {
        backgroundExecutor.submit {
            writeToFileWithLock("Wrote to file from background thread")

            handler.post {
                writeToFileWithLock("Wrote to file from main thread")
            }
        }
    }

    fun writeToFileWithLock(contents: String) {
        lock.withLock {
            // write file contents here...
        }
    }
}
