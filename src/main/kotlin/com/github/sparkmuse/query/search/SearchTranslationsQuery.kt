package com.github.sparkmuse.query.search

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.Query

class SearchTranslationsQuery(

    /**
     * Search string
     */
    val q: String,

    /**
     * Language code of the source language in a bilingual dataset.
     */
    val sourceLanguage: LanguageBilingual = LanguageBilingual.English,

    /**
     * Language code of the target language in a bilingual dataset.
     */
    val targetLanguage: LanguageBilingual = LanguageBilingual.Spanish,

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

    override val fragments get() = listOf("search", "translations", sourceLanguage.value, targetLanguage.value)

    override val parameters
        get() = mapOf(
            "q" to q,
            "prefix" to prefix.toString(),
            "limit" to limit.toString(),
            "offset" to offset.toString()
        )
}

