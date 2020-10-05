package org.kubinity.soap.client.soap

import javax.xml.soap.SOAPMessage

class SoapClient(private val soapConnection: SoapConnection, private val credentials: SoapCredentials) {
    fun call(soapMessage: SOAPMessage): SOAPMessage {
        // Add headers
        soapMessage.mimeHeaders.addHeader("Authorization", "Basic ${credentials.getBasicAuth()}")
        soapMessage.mimeHeaders.addHeader("Content-type", "text/xml")

        // Send the message
        return soapConnection.call(soapMessage)
    }
}
