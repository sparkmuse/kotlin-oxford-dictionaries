package com.github.sparkmuse.example

import com.github.sparkmuse.OxfordClient
import com.github.sparkmuse.query.DataField
import com.github.sparkmuse.query.EntryQuery
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.utility.GrammaticalFeatureMonolingualQuery
import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.net.http.HttpClient

@ExtendWith(WiremockExtension::class)
class ExampleKotlin {

    @Wiremock
    lateinit var mockServer: WireMockServer

    @BeforeEach
    fun setUp() {
        mockServer.stubFor(
            WireMock.get(anyUrl())
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

    @Test
    fun `gets entries for the word 'ace' with complex query`() {

        val appId = System.getenv("APP_ID")
        val appKey = System.getenv("APP_KEY")
        val baseUrl = "https://od-api.oxforddictionaries.com/api/v2"

        val oxfordClient = OxfordClient(appId, appKey, baseUrl)

        val query = EntryQuery(
            word = "ace",
            sourceLanguage = LanguageMonolingual.English_us,
            fields = listOf(DataField.definitions),
            lexicalCategory = listOf("noun"),
            strictMatch = true
        )

        val entries = oxfordClient.entries(query)
        assertNotNull(entries)
    }

    @Test
    fun `gets grammatical features for 'en-us' language`() {

        val appId = System.getenv("APP_ID")
        val appKey = System.getenv("APP_KEY")
        val baseUrl = "https://od-api.oxforddictionaries.com/api/v2"

        val oxfordClient = OxfordClient(appId, appKey, baseUrl)

        val query = GrammaticalFeatureMonolingualQuery(
            sourceLanguage = LanguageMonolingual.English_us
        )

        val grammaticalFeature = oxfordClient.grammaticalFeatures(query)
        assertNotNull(grammaticalFeature)
    }
}