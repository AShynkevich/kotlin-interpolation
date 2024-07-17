package com.example.network

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

const val PORT = 9000

fun main() {
    val listenAddress = InetSocketAddress(PORT)
    val channel = DatagramChannel.open()
    channel.bind(listenAddress)

    println("Listening for broadcast messages on port $PORT")

    val buffer = ByteBuffer.allocate(256)

    while (true) {
        buffer.clear()
        channel.receive(buffer)

        val receivedMessage = String(buffer.array()).trim { it <= ' ' }
        println("Received broadcast message: $receivedMessage")
    }
}