package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.Query

/**
 * Lists available filters
 */
class FilterQuery : Query {

    /**
     * Get gets the parameters of the call as a map of strings
     */
    override fun parameters(): Map<String, String> {
        return mapOf()
    }

    /**
     * Get the url path fragment for the call
     */
    override fun pathFragment(): String {
        return "filters"
    }
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

    /**
     * Get gets the parameters of the call as a map of strings
     */
    override fun parameters(): Map<String, String> {
        return mapOf()
    }

    /**
     * Get the url path fragment for the call
     */
    override fun pathFragment(): String {
        return "filters/$endpoint"
    }
}