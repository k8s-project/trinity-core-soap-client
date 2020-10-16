package org.kubinity.soap.client.soap

import org.apache.commons.codec.binary.Base64

data class SoapCredentials(private val username: String, private val password: String) {
    fun getBasicAuth() : String {
        return Base64.encodeBase64String("$username:$password".toByteArray())
    }
}
