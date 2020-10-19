package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.Query

/**
 * Lists available fields
 */
class FieldQuery : Query {

    override val queryParams: String get() = ""

    override val pathFragment: String
        get() = "fields"
}

/**
 * Lists available fields for specific endpoint
 */
class FieldEndpointQuery(

    /**
     * path endpoint
     */
    val endpoint: String

) : Query {

    override val queryParams: String get() = ""

    override val pathFragment: String
        get() = "fields/$endpoint"
}