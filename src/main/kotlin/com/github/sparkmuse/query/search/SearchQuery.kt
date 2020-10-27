package com.github.sparkmuse.query.search

import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.Query

class SearchQuery(

    /**
     * Search string
     */
    val q: String,

    /**
     * Language code of the source language in a monolingual dataset.
     */
    val sourceLanguage: LanguageMonolingual = LanguageMonolingual.English_gb,

    /**
     * Use prefix=true to return only results that start with the value of the "q" parameter.
     */
    val prefix: Boolean = false,

    /**
     * Restricts number of returned results. Default and max. is 5000.
     */
    val limit: Int = 5000,

    /**
     * pagination - results offset. The sum of offset and limit must not exceed 10000.
     */
    val offset: Int = 0

) : Query {

    override val fragments get() = listOf("search", sourceLanguage.value)

    override val parameters
        get() = mapOf(
            "q" to q,
            "prefix" to prefix.toString(),
            "limit" to limit.toString(),
            "offset" to offset.toString()
        )
}

