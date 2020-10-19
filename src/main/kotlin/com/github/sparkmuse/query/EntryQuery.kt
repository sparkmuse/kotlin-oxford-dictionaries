package com.github.sparkmuse.query

class EntryQuery(

    /**
     * The identifier for an Entry (case-sensitive)
     */
    val word: String,

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: LanguageMonolingual = LanguageMonolingual.English_gb,

    /**
     * A comma-separated list of data fields to return for the matched entries.
     * Valid field names are 'definitions', 'domains', 'etymologies', 'examples', 'pronunciations',
     * 'regions', 'registers' and 'variantForms'.
     * (a) If not specified, all available fields for each word_id are returned.
     * (b) If specified and empty, the minimum payload for each word_id is returned.
     * (c) If more field names are specified, then the minimum payload plus the specified fields for each word_id are returned.
     */
    val fields: List<DataField> = listOf(),

    /**
     * Selection filter: a comma-separated list of grammatical features ids to match on (default: all features).
     * The available grammatical features for a given language (or language pair) can be obtained from
     * the /grammaticalfeatures/ endpoint.
     *
     * The filter keeps all the entries in the response whose grammaticalFeatures "id" matches the values in
     * the grammaticalFeatures parameter.
     */
    val grammaticalFeatures: List<String> = listOf(),

    /**
     * Selection filter: a comma-separated list of lexical categories ids to match on (default: all categories).
     * The available lexical categories for a given language (or language pair) can be obtained from
     * the /lexicalcategories/ endpoint.
     *
     * The filter keeps all the entries in the response whose lexicalCategory "id" matches the values in the
     * lexicalCategory parameter.
     */
    val lexicalCategory: List<String> = listOf(),

    /**
     * Selection filter: a comma-separated list of domains ids to match on (default: all domains).
     * The available domains for a given language (or language pair) can be obtained from the /domains/ endpoint.
     *
     * The filter keeps all the senses and subsenses in the response whose domains "id" matches the values in the
     * domains parameter.
     */
    val domains: List<String> = listOf(),

    /**
     * Selection filter: a comma-separated list of registers ids to match on (default: all registers).
     * The available registers for a given language (or language pair) can be obtained from the /registers/ endpoint.
     *
     * The filter keeps all the senses and subsenses in the response whose registers "id" matches the values in the
     * registers parameter.
     */
    val registers: List<String> = listOf(),

    /**
     * Specifies whether diacritics must match exactly. If "false", near-homographs for the given word_id will
     * also be selected (e.g., rose matches both rose and rosé; similarly rosé matches both).
     */
    val strictMatch: Boolean = false

) : Query {

    override val queryParams: String
        get() {
            return mapOf(
                "fields" to fields.joinWithComma(),
                "grammaticalFeatures" to grammaticalFeatures.joinWithComma(),
                "lexicalCategory" to lexicalCategory.joinWithComma(),
                "domains" to domains.joinWithComma(),
                "registers" to registers.joinWithComma(),
                "strictMatch" to strictMatch.toString()
            )
                .filterValues { it.isNotEmpty() }
                .joinWithAmpersand()
        }

    override val pathFragment: String
        get() = "entries/${sourceLanguage.value}/$word"
}