package org.example

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.StandardSocketOptions
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

fun main() {
    val groupAddress = InetAddress.getByName("230.0.0.1")
    val networkInterface = getDefaultInterface()
    // Open the channel and bind it
    val channel = DatagramChannel.open().apply {
        setOption(StandardSocketOptions.SO_REUSEADDR, true)
        bind(InetSocketAddress(9000))
        join(groupAddress, networkInterface)
    }

    val buffer = ByteBuffer.allocate(256)

    println("Starting server")
    try {
        while(true) {
            buffer.clear()
            val clientAddress = channel.receive(buffer)

            buffer.flip()
            val clientMessage = Charsets.UTF_8.decode(buffer).toString()

            println("Received message: '$clientMessage' from: $clientAddress")
        }
    } catch (exception: Exception) {
        println("An error occurred: ${exception.message}")
        channel.close()
    } finally {
        println("Server execution complete.")
    }
}