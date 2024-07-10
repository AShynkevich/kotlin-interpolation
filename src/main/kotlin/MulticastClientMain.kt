package org.example

import java.net.InetAddress
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

fun main() {
    val groupAddress = InetAddress.getByName("230.0.0.1")
    val channel = DatagramChannel.open()

    val networkInterface = getDefaultInterface()

    channel.setOption(java.net.StandardSocketOptions.SO_REUSEADDR, true)
    channel.bind(InetSocketAddress(9000))
    channel.join(groupAddress, networkInterface)

    val buffer = ByteBuffer.wrap("Hello, Multicast Group!".toByteArray(Charsets.UTF_8))
    channel.send(buffer, InetSocketAddress(groupAddress, 9000))

    channel.close()

    println("Message sent to the multicast group")
}