package trinity.core.soap.client.soap

import java.net.URL
import javax.xml.soap.SOAPConnection
import javax.xml.soap.SOAPMessage

class SoapConnection (connectionSupplier: () -> SOAPConnection, private val url: URL) {
    private val connection: SOAPConnection = connectionSupplier.invoke()

    fun call(soapMessage: SOAPMessage): SOAPMessage {
        return this.connection.call(soapMessage, url)
    }

    fun get(): SOAPMessage {
        return this.connection.get(url)
    }

    fun close() {
        this.connection.close()
    }
}