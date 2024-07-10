package org.example

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SocketChannel
import java.nio.charset.StandardCharsets

fun main() {
    val clientChannel = SocketChannel.open(InetSocketAddress("localhost", 9000))

    val buffer = ByteBuffer.wrap("Hello, Server!".toByteArray(StandardCharsets.UTF_8))
    clientChannel.write(buffer)

    val readBuffer = ByteBuffer.allocate(256)
    clientChannel.read(readBuffer)

    val receivedMessage = String(readBuffer.array()).trim { it <= ' ' }
    println("Message from server: $receivedMessage")
}