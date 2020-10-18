package com.github.sparkmuse.query

interface Query {
    fun parameters(): Map<String, String>
    fun pathFragment(): String
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
enum class LanguageBilingual() {
    en, ar, de, el, es, ha, hi, id, it, mr, ms, nso, pt, qu, ru, te, tk, tn, tpi, tt, ur, xh, zh, zu;
}

enum class LanguageThesaurus() {
    en
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