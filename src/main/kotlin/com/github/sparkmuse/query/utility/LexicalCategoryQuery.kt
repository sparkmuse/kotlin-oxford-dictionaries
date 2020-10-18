package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.Query

/**
 * Lists available LexicalCategory in a monolingual dataset
 */
class LexicalCategoryMonolingualQuery(

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: LanguageMonolingual = LanguageMonolingual.English_gb,

    ) : Query {

    /**
     * Get gets the parameters of the call as a map of strings
     */
    override fun parameters(): Map<String, String> {
        return mapOf()
    }

    /**
     * Get the url path fragment for the call
     */
    override fun pathFragment(): String {
        return "lexicalCategories/${sourceLanguage.value}"
    }
}

/**
 * Lists available LexicalCategory in a bilingual dataset
 */
class LexicalCategoryBilingualQuery(

    /**
     * Language code of the source language in a bilingual dataset.
     */
    val sourceLanguage: LanguageBilingual = LanguageBilingual.English,

    /**
     * Language code of the target language in a bilingual dataset.
     */
    val targetLanguage: LanguageBilingual = LanguageBilingual.Spanish,

    ) : Query {

    /**
     * Get gets the parameters of the call as a map of strings
     */
    override fun parameters(): Map<String, String> {
        return mapOf()
    }

    /**
     * Get the url path fragment for the call
     */
    override fun pathFragment(): String {
        return "lexicalCategories/${sourceLanguage.value}/${targetLanguage.value}"
    }
}