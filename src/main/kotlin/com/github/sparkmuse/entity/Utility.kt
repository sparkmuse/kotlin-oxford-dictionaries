package com.github.sparkmuse.entity

data class RetrieveDomain(
    val metadata: Map<String, String> = mapOf(),
    val results: Map<String, Map<String, String>> = mapOf()
)

data class RetrieveField(
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

data class RetrieveGrammaticalFeature(
    val metadata: Map<String, String> = mapOf(),
    val results: Map<String, Map<String, Map<String, String>>> = mapOf()
)

data class RetrieveLanguage(
    val metadata: Map<String, String> = mapOf(),
    val results: List<Result> = listOf()
) {

    data class Result(
        val source: String = "",
        val sourceLanguage: Map<String, String> = mapOf(),
        val targetLanguage: Map<String, String> = mapOf(),
        val type: String = "",
        val region: String = ""
    )
}

data class RetrieveLexicalCategory(
    val metadata: Map<String, String> = mapOf(),
    val results: Map<String, Map<String, String>> = mapOf()
)

data class RetrieveRegister(
    val metadata: Map<String, String> = mapOf(),
    val results: Map<String, Map<String, String>> = mapOf(),
)