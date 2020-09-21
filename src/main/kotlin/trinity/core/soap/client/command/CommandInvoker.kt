package trinity.core.soap.client.command

import trinity.core.soap.client.soap.SoapClient
import javax.xml.soap.SOAPMessage

class CommandInvoker(private val soapClient: SoapClient) {
    fun invoke(command: Command): SOAPMessage {
        return soapClient.call(command.getSoapMessage())
    }
}