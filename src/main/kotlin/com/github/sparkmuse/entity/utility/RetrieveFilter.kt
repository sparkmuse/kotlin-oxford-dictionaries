package com.github.sparkmuse.entity.utility

data class RetrieveFilter(
    val metadata: Map<String, String> = mapOf(),
    val results: Results = Results()
) {
    data class Results(
        val entries: List<String> = listOf(),
        val lemmas: List<String> = listOf(),
        val sentences: List<String> = listOf(),
        val thesaurus: List<String> = listOf(),
        val translations: List<String> = listOf()
    )
}