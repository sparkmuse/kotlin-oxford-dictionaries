package com.github.sparkmuse.query

interface Query {
    /**
     * Get the url path fragment for the call
     */
    val pathFragment: String

    /**
     * Get gets the parameters of the call as a map of strings
     */
    val queryParams: String
}

internal fun <T> Iterable<T>.joinWithComma(): String {
    return joinToString(",")
}

internal fun <K, V> Map<K, V>.joinWithAmpersand(): String {
    return entries.joinToString("&")
}

/**
 * Language code of the source language in a monolingual dataset.
 */
enum class LanguageMonolingual(val value: String) {
    English_gb("en-gb"),
    English_us("en-us"),
    Spanish("es"),
    French("fr"),
    Gujarati("gu"),
    Hindi("hi"),
    Latvian("lv"),
    Romanian("ro"),
    Swahili("sw"),
    Tamil("ta")
}

/**
 * Language code of the source language in a bilingual dataset.
 */
enum class LanguageBilingual(val value: String) {
    English("en"),
    Arabic("ar"),
    German("de"),
    Greek("el"),
    Spanish("es"),
    Hausa("ha"),
    Hindi("hi"),
    Indonesian("id"),
    Italian("it"),
    Marathi("mr"),
    Malay("ms"),
    Northern_Sotho("nso"),
    Portuguese("pt"),
    Quechua("qu"),
    Russian("ru"),
    Telugu("te"),
    Turkmen("tk"),
    Tswana("tn"),
    Tok_Pisin("tpi"),
    Tatar("tt"),
    Urdu("ur"),
    Xhosa("xh"),
    Chinese("zh"),
    Zulu("zu")
}

/**
 * Language code of the source language in a Thesaurus dataset.
 */
enum class LanguageThesaurus(val value: String) {
    English("en")
}

/**
 * Language code of the source language. Used for sentences
 */
enum class LanguageSentence(val value: String) {
    English("en"),
    Spanish("es")
}


/**
 * A comma-separated list of data fields to return for the matched entries.
 * Valid field names are 'definitions', 'domains', 'etymologies', 'examples', 'pronunciations',
 * 'regions', 'registers' and 'variantForms'.
 * (a) If not specified, all available fields for each word_id are returned.
 * (b) If specified and empty, the minimum payload for each word_id is returned.
 * (c) If more field names are specified, then the minimum payload plus the specified fields for each word_id are returned.
 */
enum class DataField {
    definitions,
    domains,
    etymologies,
    examples,
    pronunciations,
    regions,
    registers,
    variantForms
}