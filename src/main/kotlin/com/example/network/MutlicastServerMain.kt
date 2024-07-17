package com.example.network

import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.StandardProtocolFamily
import java.net.StandardSocketOptions
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

fun main() {
    val groupAddress = InetAddress.getByName("230.0.0.0")
    val networkInterface = getDefaultInterface()
    val ips = networkInterface.inetAddresses.toList()
        .map { it.hostAddress.toString() }
        .toList()
        .joinToString(", ")
    println("The current IP: [$ips]")
    // Open the channel and bind it
    val channel = DatagramChannel.open(StandardProtocolFamily.INET).apply {
        setOption(StandardSocketOptions.SO_REUSEADDR, true)
        bind(InetSocketAddress("0.0.0.0",9000))
        setOption(StandardSocketOptions.IP_MULTICAST_IF, networkInterface)
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