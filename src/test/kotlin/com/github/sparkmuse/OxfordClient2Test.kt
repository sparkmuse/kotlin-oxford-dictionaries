package com.github.sparkmuse

import com.github.sparkmuse.entity.RetrieveEntry
import com.github.sparkmuse.entity.WordList
import com.github.sparkmuse.query.EntryQuery
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.search.SearchQuery
import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(WiremockExtension::class)
class OxfordClient2Test {

    @Wiremock
    lateinit var wiremock: WireMockServer

    @Test
    fun `all headers, correct path, query parameters returns wordlist`() {

        val client = OxfordClient2("appId", "appKey", wiremock.baseUrl())
        val query = EntryQuery("ace")

        wiremock.stubFor(
            get(urlPathMatching("/${query.pathFragment()}"))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("app_id", equalTo(client.appId))
                .withHeader("app_key", equalTo(client.appKey))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("entries.json")
                )
        )

        val actual: RetrieveEntry = client.execute(query)

        assertThat(actual).isNotNull
    }

    @Test
    fun `throws exception when there is an error`() {

        val client = OxfordClient2("appId", "appKey", wiremock.baseUrl())
        val query = EntryQuery("ace")

        wiremock.stubFor(
            get(urlPathMatching("/${query.pathFragment()}"))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("app_id", equalTo(client.appId))
                .withHeader("app_key", equalTo(client.appKey))
                .withQueryParam("strictMatch", equalTo("false"))
                .willReturn(
                    aResponse()
                        .withBody("Authentication failed")
                        .withStatus(403)
                )
        )

        assertThatThrownBy { client.execute(query) }
            .isInstanceOf(OxfordClient2.OxfordClientException::class.java)
            .hasMessage("StatusCode: 403 Error: Authentication failed")
    }
}
