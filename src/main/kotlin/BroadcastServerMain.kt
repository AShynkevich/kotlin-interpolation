package org.example

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

fun main() {
    val listenAddress = InetSocketAddress(9000)
    val channel = DatagramChannel.open()
    channel.bind(listenAddress)

    println("Listening for broadcast messages on port 9000")

    val buffer = ByteBuffer.allocate(256)

    while (true) {
        buffer.clear()
        channel.receive(buffer)

        val receivedMessage = String(buffer.array()).trim { it <= ' ' }
        println("Received broadcast message: $receivedMessage")
    }
}