package trinity.core.soap.client

import trinity.core.soap.client.command.CommandInvoker
import trinity.core.soap.client.command.MiscCommand
import trinity.core.soap.client.soap.SoapClient
import trinity.core.soap.client.soap.SoapConnection
import trinity.core.soap.client.soap.SoapCredentials
import java.net.URL
import javax.xml.soap.SOAPConnectionFactory

fun main() {
    val connection = SoapConnection(SOAPConnectionFactory.newInstance()::createConnection, URL("http://127.0.0.1:7878"))
    val credentials = SoapCredentials("archcry", "PotLood1")
    val client = SoapClient(connection, credentials)

    val command = MiscCommand.notify("Hello World!")

    val commandInvoker = CommandInvoker(client)

    commandInvoker.invoke(command).writeTo(System.out)
}
