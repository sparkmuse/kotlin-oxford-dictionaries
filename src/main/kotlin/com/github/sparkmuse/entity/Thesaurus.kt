package com.github.sparkmuse.entity

/**
 * A thesaurus allows for the retrieval of words similar in meaning to another word (synonyms) or opposite in meaning (antonyms).
 * Example: 'Enthusiastic' is synonymous with 'keen', 'avid', and 'energetic', and its antonym is 'apathetic'.
 *
 * https://developer.oxforddictionaries.com/documentation#!/Thesaurus/get_thesaurus_source_lang_word_id
 * https://developer.oxforddictionaries.com/documentation/glossary
 *
 */
data class Thesaurus(
    val metadata: Map<String, String> = mapOf(),
    val results: List<HeadwordThesaurus> = listOf()
) {
    data class HeadwordThesaurus(
        val id: String = "",
        val language: String = "",
        val lexicalEntries: List<LexicalEntry> = listOf(),
        val type: String = "",
        val word: String = ""
    )
}