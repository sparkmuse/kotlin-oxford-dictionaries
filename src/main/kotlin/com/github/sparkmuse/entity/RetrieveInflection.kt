package com.github.sparkmuse.entity

/**
 * An inflection is a change in the form of a word to express a grammatical function such as tense, mood, person, number, case, or gender.
 * Example: ‘foxes’ is an inflected form of ‘fox’.
 *
 * https://developer.oxforddictionaries.com/documentation#!/Inflections/get_inflections_source_lang_word_id
 * https://developer.oxforddictionaries.com/documentation/glossary
 *
 */
data class RetrieveInflection(
    val metadata: Map<String, String> = mapOf(),
    val results: List<Result> = listOf()
) {
    data class Result(
        val id: String = "",
        val language: String = "",
        val lexicalEntries: List<LexicalEntry> = listOf(),
        val text: String = ""
    ) {
        data class LexicalEntry(
            val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
            val inflections: List<Inflection> = listOf(),
            val language: String = "",
            val lexicalCategory: LexicalCategory = LexicalCategory()
        )
    }
}