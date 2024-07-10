package org.example

import java.net.NetworkInterface

fun getDefaultInterface(): NetworkInterface {
    return NetworkInterface.getNetworkInterfaces()
        .toList().first { inter ->
            inter.inetAddresses.toList().any {
                it.isSiteLocalAddress &&
                        (it.hostAddress.startsWith("192.168.1") || it.hostAddress.startsWith("172."))
            }
        }
}