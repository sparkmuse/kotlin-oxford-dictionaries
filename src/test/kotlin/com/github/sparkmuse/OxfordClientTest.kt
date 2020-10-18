package com.github.sparkmuse

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageBilingual.English
import com.github.sparkmuse.query.LanguageBilingual.Spanish
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.utility.*
import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(WiremockExtension::class)
class OxfordClientTest {

    @Wiremock
    lateinit var wiremock: WireMockServer

    lateinit var oxfordClient: OxfordClient

    @BeforeEach
    fun setUp() {
        oxfordClient = OxfordClient("appId", "appKey", wiremock.baseUrl())
    }

    @Test
    fun `gets entries`() {

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

        wiremock.stubFor(
            get(urlPathMatching("/search/translations/en/es"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("search/search_translations.json")
                )
        )

        val retrieveEntry = oxfordClient.searchTranslations("ace")

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun `gets search thesaurus`() {

        wiremock.stubFor(
            get(urlPathMatching("/search/translations/en/es"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("search/search_thesaurus.json")
                )
        )

        val retrieveEntry = oxfordClient.searchTranslations("ace")

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun `gets search`() {

        wiremock.stubFor(
            get(urlPathMatching("/search/en-gb"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("search/search.json")
                )
        )

        val retrieveEntry = oxfordClient.search("ace")

        assertThat(retrieveEntry).isNotNull
    }


    @Test
    fun thesaurus() {

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

        wiremock.stubFor(
            get(urlPathMatching("/sentences/en/ace"))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("sentences.json")
                )
        )

        val sentencesResults = oxfordClient.sentences("ace")

        assertThat(sentencesResults).isNotNull
    }

    @Test
    fun words() {

        wiremock.stubFor(
            get(urlPathMatching("/words/en-gb"))
                .withQueryParam("q", equalTo("ace"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("entries.json")
                )
        )

        val results = oxfordClient.words("ace")

        assertThat(results).isNotNull
    }

    @Test
    fun domainMonolingual() {
        wiremock.stubFor(
            get(urlPathMatching("/domains/en-gb"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("utility/domains.json")
                )
        )

        val results = oxfordClient.domain(DomainMonolingualQuery(English_gb))

        assertThat(results).isNotNull
    }

    @Test
    fun domainBilingual() {
        wiremock.stubFor(
            get(urlPathMatching("/domains/en/es"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("utility/domains.json")
                )
        )

        val results = oxfordClient.domain(DomainBilingualQuery(English, Spanish))

        assertThat(results).isNotNull
    }

    @Test
    fun field() {
        wiremock.stubFor(
            get(urlPathMatching("/fields"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("utility/fields.json")
                )
        )

        val results = oxfordClient.field(FieldQuery())

        assertThat(results).isNotNull
    }

    @Test
    fun `field for specific endpoint`() {
        wiremock.stubFor(
            get(urlPathMatching("/fields/entries"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("utility/fields.json")
                )
        )

        val results = oxfordClient.field(FieldEndpointQuery("entries"))

        assertThat(results).isNotNull
    }

    @Test
    fun filter() {
        wiremock.stubFor(
            get(urlPathMatching("/filters"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("utility/filters.json")
                )
        )

        val results = oxfordClient.filter(FilterQuery())

        assertThat(results).isNotNull
    }

    @Test
    fun `filter for specific endpoint`() {
        wiremock.stubFor(
            get(urlPathMatching("/filters/entries"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("utility/filters.json")
                )
        )

        val results = oxfordClient.filter(FilterEndpointQuery("entries"))

        assertThat(results).isNotNull
    }
}