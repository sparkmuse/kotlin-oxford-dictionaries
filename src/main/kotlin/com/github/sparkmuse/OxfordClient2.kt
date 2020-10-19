package com.github.sparkmuse

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sparkmuse.query.Query
import mu.KotlinLogging
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS

class OxfordClient2(
    val appId: String,
    val appKey: String,
    val baseUrl: String = "https://od-api.oxforddictionaries.com/api/v2"
) {

    internal inline fun <reified T> execute(query: Query): T {

        val url = createUri(query)

        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(url)
            .header("Accept", "application/json")
            .header("app_id", appId)
            .header("app_key", appKey)
            .timeout(Duration.of(10, SECONDS))
            .GET()
            .build()

        val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())

        return when (response.statusCode()) {
            200 -> jacksonObjectMapper().readValue(response.body(), T::class.java)
            else -> {
                throw OxfordClientException("StatusCode: ${response.statusCode()} Error: ${response.body()}")
            }
        }
    }

    class OxfordClientException(message: String) : Exception(message)

    private fun createUri(query: Query): URI {

        val queryParams = query
            .parameters()
            .entries
            .joinToString(separator = "&")

        return URI("$baseUrl//${query.pathFragment()}?$queryParams").normalize()
    }
}