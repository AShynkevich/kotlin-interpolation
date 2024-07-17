package com.example.network

import java.net.NetworkInterface

fun getDefaultInterface(): NetworkInterface {
    return NetworkInterface.getNetworkInterfaces()
        .toList().first { inter ->
            inter.inetAddresses.toList().any {
                it.isSiteLocalAddress && !it.isLoopbackAddress &&
                        it.hostAddress.indexOf(":") == -1
            }
        }
}
