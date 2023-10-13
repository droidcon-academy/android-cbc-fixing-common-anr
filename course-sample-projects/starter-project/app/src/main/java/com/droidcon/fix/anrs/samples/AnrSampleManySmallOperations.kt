package com.droidcon.fix.anrs.samples

/**
 * Schedule too much work for the main thread to handle.
 */
fun anrByManySmallOperations() {
    performWork()
}

private fun performWork() {
    val aJson = readFromDisk("a.json")
    val a = deserializeJson(aJson)

    val bJson = readFromDisk("b.json")
    val b = deserializeJson(bJson)

    val cJson = readFromDisk("c.json")
    val c = deserializeJson(cJson)

    val dJson = readFromDisk("d.json")
    val d = deserializeJson(dJson)
}

private fun readFromDisk(path: String): String {
    Thread.sleep(1500) // simulate a slow disk read.
    return ""
}

private fun deserializeJson(json: String): String {
    Thread.sleep(1200) // simulate parsing a large JSON file.
    return "{}"
}

