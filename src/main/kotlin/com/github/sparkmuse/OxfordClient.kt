package com.github.sparkmuse

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sparkmuse.common.Query
import com.github.sparkmuse.entries.EntryQuery
import com.github.sparkmuse.entries.RetrieveEntry
import com.github.sparkmuse.lemmas.LemmaQuery
import com.github.sparkmuse.lemmas.Lemmatron
import mu.KotlinLogging
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

private val logger = KotlinLogging.logger {}

class OxfordClient(
    val appId: String,
    val appKey: String,
    val baseUrl: String = "https://od-api.oxforddictionaries.com/api/v2"
) {

    private val client = OkHttpClient()

    /**
     * /api/v2/entries/{source_lang}/{word_id}:
     * Use this to retrieve definitions, pronunciations, example sentences, grammatical information and word origins
     *
     * TIP: Entries ONLY works for dictionary headwords. You may need to use the Lemmas endpoint first to link an
     * inflected form back to its headword (e.g., pixels --> pixel).
     *
     * Use filters to limit the entry information that is returned.
     * For example, you may only require definitions and not everything else, or just pronunciations
     *
     * The full list of filters can be retrieved from the filters Utility endpoint.
     * You can also specify values within the filter using '='. For example 'grammaticalFeatures=singular'.
     *
     * Filters can also be combined.
     * Combining different filters will build a query using 'AND' operators, while if a filter contains more
     * than one value it will build a query using 'OR' operators. For example, a combination of filters
     * like '?grammaticalFeatures=singular&lexicalCategory=noun,verb' will return entries which match the
     * query ('noun' OR 'verb') AND 'singular'.
     */
    fun entries(query: EntryQuery): RetrieveEntry? {
        val httpUrl = createUrl(query)
        val request = createRequest(httpUrl)
        return call(request)
    }

    /**
     * @see OxfordClient.entries
     */
    fun entries(word: String): RetrieveEntry? {
        return entries(EntryQuery(word))
    }

    fun lemmas(query: LemmaQuery): Lemmatron? {
        val httpUrl = createUrl(query)
        val request = createRequest(httpUrl)
        return call(request)
    }

    /**
     * @see OxfordClient.lemmas
     */
    fun lemmas(word: String): Lemmatron? {
        return lemmas(LemmaQuery(word))
    }

    /**
     * Makes the actual call using the client.
     */
    private inline fun <reified T> call(request: Request): T? {
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                logger.error { response.message }
                return null
            }
            return parse<T>(response)
        }
    }

    /**
     * Parses the response from the client and returns the parse object.
     */
    private inline fun <reified T> parse(response: Response): T? {
        return try {
            val json = response.body?.string()
            jacksonObjectMapper().readValue(json, T::class.java)
        } catch (ex: Exception) {
            logger.error { ex.message }
            return null
        }
    }

    /**
     * Creates the url to get the entries
     */
    private fun createUrl(query: Query): HttpUrl {
        return baseUrl
            .toHttpUrl()
            .newBuilder()
            .addPathSegments(query.pathFragment())
            .apply { query.parameters().forEach { (key, value) -> addQueryParameter(key, value) } }
            .build()
    }

    /**
     * Generica method for a request. It adds all needed headers.
     */
    private fun createRequest(httpUrl: HttpUrl): Request {
        return Request.Builder()
            .header("User-Agent", "OkHttp Headers.java")
            .addHeader("Accept", "application/json")
            .addHeader("app_id", appId)
            .addHeader("app_key", appKey)
            .url(httpUrl)
            .build()
    }
}

