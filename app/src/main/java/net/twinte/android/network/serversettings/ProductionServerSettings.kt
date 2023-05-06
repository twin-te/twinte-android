package net.twinte.android.network.serversettings

class ProductionServerSettings : ServerSettings {
    override val twinteBackendApiEndpointScheme: String = "https"
    override val twinteBackendApiEndpointHost: String = "app.twinte.net"
}
