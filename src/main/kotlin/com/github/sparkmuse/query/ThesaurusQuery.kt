package com.github.sparkmuse.query

class ThesaurusQuery(

    /**
     * The identifier for an Entry (case-sensitive)
     */
    val word: String,

    /**
     * Language code of the source language in a Thesaurus dataset.
     */
    val sourceLanguage: LanguageThesaurus = LanguageThesaurus.English,

    /**
     * A comma-separated list of data fields to return for the matched entries.
     * What to return - if specified, either 'synonyms', 'antonyms' or 'synonyms,antonyms'
     * It cannot be empty.
     */
    val fields: List<DataField> = listOf(),

    /**
     * Specifies whether diacritics must match exactly. If "false", near-homographs for the given word_id will
     * also be selected (e.g., rose matches both rose and rosé; similarly rosé matches both).
     */
    val strictMatch: Boolean = false

) : Query {

    override val fragments get() = listOf("thesaurus", sourceLanguage.value, word)

    override val parameters
        get() = mapOf(
            "fields" to fields.joinWithComma(),
            "strictMatch" to strictMatch.toString()
        )

    enum class DataField {
        antonyms, synonyms
    }
}