package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.Query

/**
 * Lists available filters
 */
class FilterQuery : Query {

    override val queryParams: String get() = ""

    override val pathFragment: String
        get() = "filters"
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

    override val queryParams: String get() = ""

    override val pathFragment: String
        get() = "filters/$endpoint"
}