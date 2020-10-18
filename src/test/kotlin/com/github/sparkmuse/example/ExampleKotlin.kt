package com.github.sparkmuse.example

import com.github.sparkmuse.OxfordClient
import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(WiremockExtension::class)
class ExampleKotlin {

    @Wiremock
    private val mockServer: WireMockServer? = null

    @BeforeEach
    fun setUp() {
        mockServer!!.stubFor(
            WireMock.get(WireMock.urlEqualTo("https://od-api.oxforddictionaries.com/api/v2/entries/en-gb/ace"))
                .withQueryParam("strictMatch", WireMock.equalTo("false"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withBodyFile("entries.json")
                )
        )
    }

    @Test
    fun `retrieve entries for the word 'ace'`() {

        val appId = System.getenv("APP_ID")
        val appKey = System.getenv("APP_KEY")
        val baseUrl = "https://od-api.oxforddictionaries.com/api/v2"

        val oxfordClient = OxfordClient(appId, appKey, baseUrl)

        val entries = oxfordClient.entries("ace")

        assertNotNull(entries)
    }
}