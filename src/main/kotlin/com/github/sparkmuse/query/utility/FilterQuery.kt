package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.Query

/**
 * Lists available filters
 */
class FilterQuery : Query {

    override val fragments get() = listOf("filters")

    override val parameters: Map<String, String> get() = mapOf()
}

/**
 * Lists available filters for specific endpoint
 */
class FilterEndpointQuery(

    /**
     * path endpoint
     */
    val endpoint: String

) : Query {

    override val fragments get() = listOf("filters", endpoint)

    override val parameters: Map<String, String> get() = mapOf()
}