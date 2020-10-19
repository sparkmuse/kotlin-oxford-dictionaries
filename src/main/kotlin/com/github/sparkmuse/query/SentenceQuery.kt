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

    override val queryParams: String
        get() = "strictMatch=$strictMatch"

    override val pathFragment: String
        get() = "sentences/${sourceLanguage.value}/$word"
}