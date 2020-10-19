package com.github.sparkmuse.entity

/**
 * A complete account of a particular word. This can include a word’s senses, definitions, translations, origin,
 * and any phrases featuring the word.
 *
 * https://developer.oxforddictionaries.com/documentation#!/Entries/get_entries_source_lang_word_id
 * https://developer.oxforddictionaries.com/documentation/glossary
 *
 */
data class RetrieveEntry(
    val id: String,
    val metadata: Map<String, String> = mapOf(),
    val results: List<HeadwordEntry> = listOf(),
    val word: String = ""
) {
    data class HeadwordEntry(
        val id: String,
        val language: String,
        val lexicalEntries: List<LexicalEntry> = listOf(),
        val pronunciations: List<Pronunciation> = listOf(),
        val type: String = "",
        val word: String = ""
    )
}