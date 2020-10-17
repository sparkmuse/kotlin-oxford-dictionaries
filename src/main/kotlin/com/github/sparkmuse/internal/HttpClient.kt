package com.github.sparkmuse.internal

import com.github.sparkmuse.query.Query
import mu.KotlinLogging
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

private val logger = KotlinLogging.logger {}

internal class HttpClient(
    val appId: String,
    val appKey: String,
    val baseUrl: String = "https://od-api.oxforddictionaries.com/api/v2"
) {

    private val client = OkHttpClient()

    /**
     * Executes the query to the API
     */
    fun execute(query: Query): String {
        val httpUrl = baseUrl
            .toHttpUrl()
            .newBuilder()
            .addPathSegments(query.pathFragment())
            .apply { query.parameters().forEach { (key, value) -> addQueryParameter(key, value) } }
            .build()

        val request = Request.Builder()
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json")
            .addHeader("app_id", appId)
            .addHeader("app_key", appKey)
            .url(httpUrl)
            .build()

        return httpCall(request)
    }

    /**
     * Makes the actual call using the client.
     */
    private fun httpCall(request: Request): String {
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                logger.error { response.message }
                return ""
            }
            return response.body?.string() as String
        }
    }
}