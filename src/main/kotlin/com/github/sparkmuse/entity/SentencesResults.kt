package com.github.sparkmuse.entity

/**
 * Use this to retrieve sentences extracted from a corpus of real-world language, including news and blog content.
 * This is available for English and Spanish. For English, the sentences are linked to the correct sense of the word
 * in the dictionary. In Spanish, they are linked at the headword level.
 *
 * https://developer.oxforddictionaries.com/documentation#!/Sentences/get_sentences_source_lang_word_id
 * https://developer.oxforddictionaries.com/documentation/glossary
 *
 */
data class SentencesResults(
    val metadata: Map<String, String> = mapOf(),
    val results: List<Result> = listOf()
) {

    data class Result(
        val id: String = "",
        val language: String = "",
        val lexicalEntries: List<LexicalEntry> = listOf(),
        val type: String = "",
        val word: String = ""
    ) {
        data class LexicalEntry(
            val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
            val language: String = "",
            val lexicalCategory: LexicalCategory = LexicalCategory(),
            val sentences: List<Sentence> = listOf(),
            val text: String = ""
        ) {
            data class Sentence(
                val definitions: List<String> = listOf(),
                val domains: List<Domain> = listOf(),
                val notes: List<CategorizedText> = listOf(),
                val regions: List<Region> = listOf(),
                val registers: List<Register> = listOf(),
                val senseIds: List<String> = listOf(),
                val text: String = ""
            )
        }
    }
}