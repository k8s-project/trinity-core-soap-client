package org.kubinity.soap.client.command

import org.kubinity.soap.client.soap.SoapClient

class CommandInvoker(private val soapClient: SoapClient) {
    fun invoke(command: Command) {
        val soapResponse = soapClient.call(command.getSoapMessage())

        if (soapResponse.soapBody.hasFault()) {
            throw CommandInvokeException(soapResponse.soapBody.fault.faultString)
        }
    }
}