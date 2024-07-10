package org.example

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.charset.StandardCharsets

fun main() {
    val serverChannel = ServerSocketChannel.open()
    serverChannel.bind(InetSocketAddress(9000))

    println("Server running on port 9000")

    while (true) {
        val clientChannel = serverChannel.accept()

        val buffer = ByteBuffer.allocate(256)
        clientChannel.read(buffer)

        val receivedMessage = String(buffer.array()).trim { it <= ' ' }
        println("Message from client: $receivedMessage")

        val messageToClient = "Message received"
        val bufferToClient = ByteBuffer.wrap(messageToClient.toByteArray(StandardCharsets.UTF_8))
        clientChannel.write(bufferToClient)

        clientChannel.close()
    }
}