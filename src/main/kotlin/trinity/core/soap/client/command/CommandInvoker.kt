package trinity.core.soap.client.command

import trinity.core.soap.client.soap.SoapClient

class CommandInvoker(private val soapClient: SoapClient) {
    fun invoke(command: Command) {
        val soapResponse = soapClient.call(command.getSoapMessage())

        if (soapResponse.soapBody.hasFault()) {
            throw CommandInvokeException(soapResponse.soapBody.fault.faultString)
        }
    }
}