package com.github.sparkmuse

import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(WiremockExtension::class)
class OxfordClientTest {

    @Wiremock
    lateinit var wiremock: WireMockServer

    @Test
    @DisplayName("gets the entries with default configuration")
    fun getEntries() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/entries/en-gb/ace"))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("entries.json")
                )
        )

        val retrieveEntry = oxfordClient.entries("ace")

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    @DisplayName("gets null entry when error")
    fun getEntriesError() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/entries/en-gb/ace"))
                .willReturn(
                    aResponse()
                        .withStatus(400)
                        .withBody("""{"error": "string"}""")
                )
        )

        val retrieveEntry = oxfordClient.entries("ace")

        assertThat(retrieveEntry).isNull()
    }
}