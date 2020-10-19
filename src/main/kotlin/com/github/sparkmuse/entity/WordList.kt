package com.github.sparkmuse.entity

/**
 * Use this to retrieve possible headword matches for a given string of text. The results are calculated using
 * headword matching, fuzzy matching, and lemmatization.
 *
 * https://developer.oxforddictionaries.com/documentation#!/Search/get_search_source_lang
 * https://developer.oxforddictionaries.com/documentation/glossary
 *
 */
data class WordList(
    val metadata: Map<String, String> = mapOf(),
    val results: List<Result> = listOf()
) {
    data class Result(
        val id: String = "",
        val label: String = "",
        val matchString: String = "",
        val matchType: String = "",
        val region: String = "",
        val score: Double = 0.0,
        val word: String = ""
    )
}
