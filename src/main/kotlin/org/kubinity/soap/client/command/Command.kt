package org.kubinity.soap.client.command

import javax.xml.soap.MessageFactory
import javax.xml.soap.SOAPMessage

class Command (private val command: String) {
    fun getSoapMessage() : SOAPMessage {
        val messageFactory = MessageFactory.newInstance()

        val soapMessage = messageFactory.createMessage()

        val soapEnvelope = soapMessage.soapPart.envelope

        soapEnvelope.encodingStyle = "http://schemas.xmlsoap.org/soap/encoding/"
        soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance")
        soapEnvelope.addNamespaceDeclaration("xsd", "http://www.w3.org/1999/XMLSchema")
        soapEnvelope.addNamespaceDeclaration("SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/")
        soapEnvelope.addNamespaceDeclaration("ns1", "urn:TC")

        val body = soapMessage.soapBody

        body.addChildElement("executeCommand", "ns1")
            .addChildElement("command")
            .addTextNode(command)

        soapMessage.saveChanges()

        return soapMessage
    }
}
