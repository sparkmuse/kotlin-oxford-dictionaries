package com.github.sparkmuse.query

class SentenceQuery(

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: LanguageSentence = LanguageSentence.English,

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


    /**
     * Get gets the parameters of the call as a map of strings
     */
    override fun parameters(): Map<String, String> {
        return mapOf(
            "strictMatch" to strictMatch.toString(),
        ).filterValues { it.isNotEmpty() };
    }

    /**
     * Get the url path fragment for the call
     */
    override fun pathFragment(): String {
        return "sentences/${sourceLanguage.value}/$word"
    }
}