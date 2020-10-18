package com.github.sparkmuse

import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(WiremockExtension::class)
class OxfordClientTest {

    @Wiremock
    lateinit var wiremock: WireMockServer

    @Test
    fun `gets entries`() {

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
    fun `gets lemmas`() {

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
    fun `gets search translations`() {

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
    fun `gets search`() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/search/en-gb"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("search.json")
                )
        )

        val retrieveEntry = oxfordClient.search("ace")

        assertThat(retrieveEntry).isNotNull
    }


    @Test
    fun thesaurus() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/thesaurus/en/ace"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("thesaurus.json")
                )
        )

        val retrieveEntry = oxfordClient.thesaurus("ace")

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun translations() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/translations/en/es/ace"))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("translations.json")
                )
        )

        val translation = oxfordClient.translations("ace")

        assertThat(translation).isNotNull
    }

    @Test
    fun sentences() {

        val oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())

        wiremock.stubFor(
            get(urlPathMatching("/sentences/en/ace"))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("sentences.json")
                )
        )

        val sentencesResults = oxfordClient.sentences(word = "ace")

        assertThat(sentencesResults).isNotNull
    }
}