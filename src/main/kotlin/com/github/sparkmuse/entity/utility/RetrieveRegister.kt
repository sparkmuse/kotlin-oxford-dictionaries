package com.github.sparkmuse.entity.utility

data class RetrieveRegister(
    val metadata: Map<String, String> = mapOf(),
    val results: Map<String, Map<String, String>> = mapOf(),
)