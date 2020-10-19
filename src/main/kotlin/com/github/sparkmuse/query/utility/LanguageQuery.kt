package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.Query
import com.github.sparkmuse.query.joinWithAmpersand

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

    override val queryParams: String
        get() {
            return mapOf(
                "sourceLanguage" to (sourceLanguage?.value ?: ""),
                "targetLanguage" to (targetLanguage?.value ?: ""),
            )
                .filterValues { it.isNotEmpty() }
                .joinWithAmpersand()
        }

    override val pathFragment: String
        get() = "languages"
}