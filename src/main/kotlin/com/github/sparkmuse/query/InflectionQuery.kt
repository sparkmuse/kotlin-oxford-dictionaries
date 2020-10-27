package com.github.sparkmuse.query

class InflectionQuery(

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: LanguageMonolingual = LanguageMonolingual.English_gb,

    /**
     * The identifier for an Entry (case-sensitive)
     */
    val word: String,

    /**
     * Specifies whether diacritics must match exactly. If "false", near-homographs for the given word_id will
     * also be selected (e.g., rose matches both rose and rosé; similarly rosé matches both).
     */
    val strictMatch: Boolean = false

) : Query {

    override val fragments get() = listOf("inflections", sourceLanguage.value, word)

    override val parameters get() = mapOf("strictMatch" to strictMatch.toString())
}