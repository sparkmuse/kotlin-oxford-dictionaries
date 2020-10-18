package com.github.sparkmuse.entity.utility

data class RetrieveLexicalCategory(
    val metadata: Map<String, String> = mapOf(),
    val results: Results = Results()
) {
    data class Results(
        val adjective: Map<String, String> = mapOf(),
        val adverb: Map<String, String> = mapOf(),
        val combiningForm: Map<String, String> = mapOf(),
        val conjunction: Map<String, String> = mapOf(),
        val contraction: Map<String, String> = mapOf(),
        val determiner: Map<String, String> = mapOf(),
        val idiomatic: Map<String, String> = mapOf(),
        val interjection: Map<String, String> = mapOf(),
        val noun: Map<String, String> = mapOf(),
        val numeral: Map<String, String> = mapOf(),
        val other: Map<String, String> = mapOf(),
        val particle: Map<String, String> = mapOf(),
        val predeterminer: Map<String, String> = mapOf(),
        val prefix: Map<String, String> = mapOf(),
        val preposition: Map<String, String> = mapOf(),
        val pronoun: Map<String, String> = mapOf(),
        val residual: Map<String, String> = mapOf(),
        val suffix: Map<String, String> = mapOf(),
        val verb: Map<String, String> = mapOf(),
    )
}