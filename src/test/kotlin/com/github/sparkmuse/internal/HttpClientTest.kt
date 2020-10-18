package com.github.sparkmuse.internal

import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.search.SearchQuery
import com.github.sparkmuse.wiremock.Wiremock
import com.github.sparkmuse.wiremock.WiremockExtension
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(WiremockExtension::class)
@DisplayName("execute the call with")
class HttpClientTest {

    @Wiremock
    lateinit var wiremock: WireMockServer

    @Test
    fun `all headers, correct path, query parameters returns string`() {

        val client = HttpClient("appId", "appKey", wiremock.baseUrl())
        val query = SearchQuery("ace", LanguageMonolingual.English_gb, true, 100, 0)

        wiremock.stubFor(
            get(urlPathMatching("/search/${query.sourceLanguage.value}"))
                .withHeader("User-Agent", equalTo("OkHttp Headers.java"))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("app_id", equalTo(client.appId))
                .withHeader("app_key", equalTo(client.appKey))
                .withQueryParam("q", equalTo(query.q))
                .withQueryParam("prefix", equalTo("${query.prefix}"))
                .withQueryParam("limit", equalTo("${query.limit}"))
                .withQueryParam("offset", equalTo("${query.offset}"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBodyFile("search/search.json")
                )
        )

        val actual = client.execute(query)

        assertThat(actual).isNotBlank
    }

    @Test
    fun `missing credentials returns empty string`() {

        val client = HttpClient("appId", "appKey", wiremock.baseUrl())
        val query = SearchQuery("ace", LanguageMonolingual.English_gb, true, 100, 0)

        wiremock.stubFor(
            get(urlPathMatching("/search/${query.sourceLanguage.value}"))
                .withHeader("User-Agent", equalTo("OkHttp Headers.java"))
                .withHeader("Accept", equalTo("application/json"))
                .withQueryParam("q", equalTo(query.q))
                .withQueryParam("prefix", equalTo("${query.prefix}"))
                .withQueryParam("limit", equalTo("${query.limit}"))
                .withQueryParam("offset", equalTo("${query.offset}"))
                .willReturn(
                    aResponse()
                        .withStatus(403)
                        .withBody("Authentication parameters missing")
                )
        )

        val actual = client.execute(query)

        assertThat(actual).isBlank
    }

    @Test
    fun `missing required parameter returns empty string`() {

        val client = HttpClient("appId", "appKey", wiremock.baseUrl())
        val query = SearchQuery("ace", LanguageMonolingual.English_gb, true, 100, 0)

        wiremock.stubFor(
            get(urlPathMatching("/search/${query.sourceLanguage.value}"))
                .withHeader("User-Agent", equalTo("OkHttp Headers.java"))
                .withHeader("Accept", equalTo("application/json"))
                .withHeader("app_id", equalTo(client.appId))
                .withHeader("app_key", equalTo(client.appKey))
                .willReturn(
                    aResponse()
                        .withStatus(400)
                        .withBody("""{"error": "parameter q is required"}""")
                )
        )

        val actual = client.execute(query)

        assertThat(actual).isBlank
    }
}