package com.github.sparkmuse

import com.github.sparkmuse.query.EntryQuery
import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.utility.*
import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
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
                .willReturn(aResponse().withStatus(200).withBodyFile("entries.json"))
        )
        val retrieveEntry = oxfordClient.entries("ace")
        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun `gets lemmas`() {
        wiremock.stubFor(
            get(urlPathMatching("/lemmas/en-gb/ace"))
                .willReturn(aResponse().withStatus(200).withBodyFile("lemmas.json"))
        )
        val retrieveEntry = oxfordClient.lemmas("ace")
        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun `gets search translations`() {
        wiremock.stubFor(
            get(urlPathMatching("/search/translations/en/es"))
                .willReturn(aResponse().withStatus(200).withBodyFile("search/search_translations.json"))
        )
        val retrieveEntry = oxfordClient.searchTranslations("ace")
        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun `gets search thesaurus`() {
        wiremock.stubFor(
            get(urlPathMatching("/search/translations/en/es"))
                .willReturn(aResponse().withStatus(200).withBodyFile("search/search_thesaurus.json"))
        )
        val retrieveEntry = oxfordClient.searchTranslations("ace")
        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun `gets search`() {
        wiremock.stubFor(
            get(urlPathMatching("/search/en-gb"))
                .willReturn(aResponse().withStatus(200).withBodyFile("search/search.json"))
        )
        val retrieveEntry = oxfordClient.search("ace")
        assertThat(retrieveEntry).isNotNull
    }


    @Test
    fun thesaurus() {
        wiremock.stubFor(
            get(urlPathMatching("/thesaurus/en/ace"))
                .willReturn(aResponse().withStatus(200).withBodyFile("thesaurus.json"))
        )
        val retrieveEntry = oxfordClient.thesaurus("ace")
        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun translations() {
        wiremock.stubFor(
            get(urlPathMatching("/translations/en/es/ace"))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(aResponse().withStatus(200).withBodyFile("translations.json"))
        )
        val translation = oxfordClient.translations("ace")
        assertThat(translation).isNotNull
    }

    @Test
    fun sentences() {
        wiremock.stubFor(
            get(urlPathMatching("/sentences/en/ace"))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(aResponse().withStatus(200).withBodyFile("sentences.json"))
        )
        val sentencesResults = oxfordClient.sentences("ace")
        assertThat(sentencesResults).isNotNull
    }

    @Test
    fun inflections() {
        wiremock.stubFor(
            get(urlPathMatching("/inflections/en-gb/ace"))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(aResponse().withStatus(200).withBodyFile("inflections.json"))
        )
        val inflection = oxfordClient.inflections("ace")
        assertThat(inflection).isNotNull
    }

    @Test
    fun words() {
        wiremock.stubFor(
            get(urlPathMatching("/words/en-gb"))
                .withQueryParam("q", equalTo("ace"))
                .willReturn(aResponse().withStatus(200).withBodyFile("entries.json"))
        )
        val results = oxfordClient.words("ace")
        assertThat(results).isNotNull
    }

    @Test
    fun domainMonolingual() {
        wiremock.stubFor(
            get(urlPathMatching("/domains/en-gb"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/domains.json"))
        )
        val results = oxfordClient.domains(DomainMonolingualQuery(English_gb))
        assertThat(results).isNotNull
    }

    @Test
    fun domainBilingual() {
        wiremock.stubFor(
            get(urlPathMatching("/domains/en/es"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/domains.json"))
        )
        val results = oxfordClient.domains(DomainBilingualQuery(LanguageBilingual.English, LanguageBilingual.Spanish))
        assertThat(results).isNotNull
    }

    @Test
    fun field() {
        wiremock.stubFor(
            get(urlPathMatching("/fields"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/fields.json"))
        )
        val results = oxfordClient.fields(FieldQuery())
        assertThat(results).isNotNull
    }

    @Test
    fun `field for specific endpoint`() {
        wiremock.stubFor(
            get(urlPathMatching("/fields/entries"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/fields.json"))
        )
        val results = oxfordClient.fields(FieldEndpointQuery("entries"))
        assertThat(results).isNotNull
    }

    @Test
    fun filter() {
        wiremock.stubFor(
            get(urlPathMatching("/filters"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/filters.json"))
        )
        val results = oxfordClient.filters(FilterQuery())
        assertThat(results).isNotNull
    }

    @Test
    fun `filter for specific endpoint`() {
        wiremock.stubFor(
            get(urlPathMatching("/filters/entries"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/filters.json"))
        )
        val results = oxfordClient.filters(FilterEndpointQuery("entries"))
        assertThat(results).isNotNull
    }

    @Test
    fun grammaticalFeaturesMonolingual() {
        wiremock.stubFor(
            get(urlPathMatching("/grammaticalFeatures/en-gb"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/grammatical_features.json"))
        )
        val results = oxfordClient.grammaticalFeatures(GrammaticalFeatureMonolingualQuery(English_gb))
        assertThat(results).isNotNull
    }

    @Test
    fun grammaticalFeaturesBilingual() {
        wiremock.stubFor(
            get(urlPathMatching("/grammaticalFeatures/en/es"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/grammatical_features.json"))
        )
        val results = oxfordClient.grammaticalFeatures(
            GrammaticalFeatureBilingualQuery(
                LanguageBilingual.English,
                LanguageBilingual.Spanish
            )
        )
        assertThat(results).isNotNull
    }

    @Test
    fun lexicalCategoriesMonolingual() {
        wiremock.stubFor(
            get(urlPathMatching("/lexicalCategories/en-gb"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/lexical_categories.json"))
        )
        val results = oxfordClient.lexicalCategories(LexicalCategoryMonolingualQuery(English_gb))
        assertThat(results).isNotNull
    }

    @Test
    fun lexicalCategoriesBilingual() {
        wiremock.stubFor(
            get(urlPathMatching("/lexicalCategories/en/es"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/lexical_categories.json"))
        )
        val results = oxfordClient.lexicalCategories(
            LexicalCategoryBilingualQuery(
                LanguageBilingual.English,
                LanguageBilingual.Spanish
            )
        )
        assertThat(results).isNotNull
    }

    @Test
    fun registersMonolingual() {
        wiremock.stubFor(
            get(urlPathMatching("/registers/en-gb"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/registers.json"))
        )
        val results = oxfordClient.registers(RegisterMonolingualQuery(English_gb))
        assertThat(results).isNotNull
    }

    @Test
    fun registersBilingual() {
        wiremock.stubFor(
            get(urlPathMatching("/registers/en/es"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/registers.json"))
        )
        val results =
            oxfordClient.registers(RegisterBilingualQuery(LanguageBilingual.English, LanguageBilingual.Spanish))
        assertThat(results).isNotNull
    }

    @Test
    fun languages() {
        wiremock.stubFor(
            get(urlPathMatching("/languages"))
                .willReturn(aResponse().withStatus(200).withBodyFile("utility/languages.json"))
        )
        val results = oxfordClient.languages(LanguageQuery(LanguageBilingual.English, LanguageBilingual.Spanish))
        assertThat(results).isNotNull
    }

    @Test
    fun `throws exception when there is an error`() {

        val client = OxfordClient("appId", "appKey", wiremock.baseUrl())
        val query = EntryQuery("ace")

        wiremock.stubFor(
            get(urlPathMatching("/${query.pathFragment}"))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("app_id", equalTo(client.appId))
                .withHeader("app_key", equalTo(client.appKey))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(aResponse().withBody("Authentication failed").withStatus(403))
        )

        assertThatThrownBy { client.execute(query) }
            .isInstanceOf(OxfordClient.OxfordClientException::class.java)
            .hasMessage("StatusCode: 403 Error: Authentication failed")
    }
}
