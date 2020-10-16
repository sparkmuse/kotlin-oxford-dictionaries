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
    @DisplayName("gets the entries")
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
    @DisplayName("gets the lemmas")
    fun getLemmas() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/lemmas/en-gb/ace"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("lemmas.json")
                )
        )

        val retrieveEntry = oxfordClient.lemmas("ace")

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    @DisplayName("gets search translations")
    fun getSearchTranslations() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/search/translations/en/es"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("search_translations.json")
                )
        )

        val retrieveEntry = oxfordClient.searchTranslations("ace")

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