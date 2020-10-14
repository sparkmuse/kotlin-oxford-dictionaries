package com.github.sparkmuse

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KotlinLogging
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

private val logger = KotlinLogging.logger {}

class OxfordClient(
    val appId: String,
    val appKey: String,
    val baseUrl: String = "https://od-api.oxforddictionaries.com/api/v2"
) {

    private val client = OkHttpClient()

    fun entries(word: String): RetrieveEntry? {

        val httpUrl = baseUrl
            .toHttpUrl()
            .newBuilder()
            .addPathSegment("entries")
            .addPathSegment("en-gb")
            .addPathSegment(word)
            .addQueryParameter("strictMatch", "false")
            .build()

        val request = Request.Builder()
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json")
            .addHeader("app_id", appId)
            .addHeader("app_key", appKey)
            .url(httpUrl)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                logger.error { response.message }
                return null
            }

            val json = response.body?.string()
            return jacksonObjectMapper().readValue(json, RetrieveEntry::class.java)
        }
    }
}