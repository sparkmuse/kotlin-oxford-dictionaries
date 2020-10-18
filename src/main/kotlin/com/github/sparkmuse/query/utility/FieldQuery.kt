package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.Query

/**
 * Lists available fields
 */
class FieldQuery : Query {

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
        return "fields"
    }
}

/**
 * Lists available fields for specific endpoint
 */
class FieldEndpointQuery(

    /**
     * Language code of the source language in a bilingual dataset.
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
        return "fields/$endpoint"
    }
}