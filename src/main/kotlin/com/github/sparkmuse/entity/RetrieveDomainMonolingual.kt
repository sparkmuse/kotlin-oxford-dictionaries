package com.github.sparkmuse.entity

data class RetrieveDomainMonolingual(
    val metadata: Map<String, String> = mapOf(),
    val results: Map<String, Map<String, String>> = mapOf()
)