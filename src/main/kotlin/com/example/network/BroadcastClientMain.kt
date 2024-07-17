package com.example.network

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

fun main() {
    val broadcastAddress = InetSocketAddress("255.255.255.255", 9000)
    val channel = DatagramChannel.open()
    channel.socket().broadcast = true

    val buffer = ByteBuffer.wrap("Hello, Network!".toByteArray(Charsets.UTF_8))

    // sends the broadcast message
    channel.send(buffer, broadcastAddress)

    channel.close()
    println("Broadcast message sent")
}