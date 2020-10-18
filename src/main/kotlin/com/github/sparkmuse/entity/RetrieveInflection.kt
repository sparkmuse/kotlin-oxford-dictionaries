package com.github.sparkmuse.entity

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