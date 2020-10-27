package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.Query

/**
 * Lists available registers in a monolingual dataset
 */
class RegisterMonolingualQuery(

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: LanguageMonolingual = LanguageMonolingual.English_gb

) : Query {

    override val fragments: List<String> = listOf("registers", sourceLanguage.value)

    override val parameters: Map<String, String> = mapOf()
}

/**
 * Lists available registers in a bilingual dataset
 */
class RegisterBilingualQuery(

    /**
     * Language code of the source language in a bilingual dataset.
     */
    val sourceLanguage: LanguageBilingual = LanguageBilingual.English,

    /**
     * Language code of the target language in a bilingual dataset.
     */
    val targetLanguage: LanguageBilingual = LanguageBilingual.Spanish

) : Query {

    override val fragments get() = listOf("registers", sourceLanguage.value, targetLanguage.value)

    override val parameters: Map<String, String> get() = mapOf()
}