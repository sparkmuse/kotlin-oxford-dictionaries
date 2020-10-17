package com.github.sparkmuse.internal

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import mu.KotlinLogging

/**
 * Parses the response from the client and returns the parse object.
 */
inline fun <reified T> parse(json: String): T? {
    val logger = KotlinLogging.logger {}
    return try {
        jacksonObjectMapper().readValue(json, T::class.java)
    } catch (ex: Exception) {
        logger.error { ex.message }
        return null
    }
}