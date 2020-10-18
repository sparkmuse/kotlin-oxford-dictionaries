package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.Query

/**
 * Returns the names of Dictionaries in the AP
 */
class LanguageQuery(

    /**
     * Language code of the source language in a bilingual dataset.
     */
    val sourceLanguage: LanguageBilingual?,

    /**
     * Language code of the target language in a bilingual dataset.
     */
    val targetLanguage: LanguageBilingual?,

    ) : Query {

    constructor() : this(null, null)

    /**
     * Get gets the parameters of the call as a map of strings
     */
    override fun parameters(): Map<String, String> {
        return mapOf(
            "sourceLanguage" to (sourceLanguage?.value ?: ""),
            "targetLanguage" to (targetLanguage?.value ?: ""),
        ).filterValues { it.isNotEmpty() }
    }

    /**
     * Get the url path fragment for the call
     */
    override fun pathFragment(): String {
        return "languages"
    }
}