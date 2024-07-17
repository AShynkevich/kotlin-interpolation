package com.example.network

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets

fun main() {
    val clientChannel = SocketChannel.open(InetSocketAddress("192.168.10.110", 4790))

    val buffer = ByteBuffer.wrap("Hello, Server!".toByteArray(StandardCharsets.UTF_8))
    clientChannel.write(buffer)

//    val readBuffer = ByteBuffer.allocate(256)
//    clientChannel.read(readBuffer)

//    val receivedMessage = String(readBuffer.array()).trim { it <= ' ' }
//    println("Message from server: $receivedMessage")
}