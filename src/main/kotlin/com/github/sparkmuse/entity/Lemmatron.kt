package com.github.sparkmuse.entity

/**
 * Lemma is a general term for any headword, phrase, or other form that can be looked up.
 * E.g. ‘act’, ‘acting’, ‘act up’, ‘get one’s act together’ are all lemmas.
 *
 * https://developer.oxforddictionaries.com/documentation#!/Lemmas/get_lemmas_source_lang_word_id
 * https://developer.oxforddictionaries.com/documentation/glossary
 */
data class Lemmatron(
    val metadata: Map<String, String> = mapOf(),
    val results: List<HeadwordLemmatron> = listOf()
) {
    data class HeadwordLemmatron(
        val id: String = "",
        val language: String = "",
        val lexicalEntries: List<LemmatronLexicalEntry> = listOf(),
        val type: String = "",
        val word: String = ""
    ) {
        data class LemmatronLexicalEntry(
            val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
            val inflectionOf: List<Inflection> = listOf(),
            val language: String = "",
            val lexicalCategory: LexicalCategory = LexicalCategory(),
            val text: String
        ) {
            data class Inflection(
                val id: String,
                val text: String
            )
        }
    }
}
