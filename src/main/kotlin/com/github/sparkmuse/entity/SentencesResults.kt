package com.github.sparkmuse.entity

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