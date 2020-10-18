package com.github.sparkmuse.entity.utility

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