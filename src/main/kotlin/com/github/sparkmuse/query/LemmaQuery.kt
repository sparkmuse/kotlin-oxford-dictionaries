package com.github.sparkmuse.query

class LemmaQuery(

    /**
     * The identifier for an Entry (case-sensitive)
     */
    val word: String,

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: SourceLanguage = SourceLanguage.English_gb,

    /**
     * Selection filter: a comma-separated list of grammatical features ids to match on (default: all features).
     * The available grammatical features for a given language (or language pair) can be obtained from
     * the /grammaticalfeatures/ endpoint. TODO: Not sure what this endpoint is
     *
     * The filter keeps all the entries in the response whose grammaticalFeatures "id" matches the values in
     * the grammaticalFeatures parameter.
     */
    val grammaticalFeatures: List<String> = listOf(),

    /**
     * Selection filter: a comma-separated list of lexical categories ids to match on (default: all categories).
     * The available lexical categories for a given language (or language pair) can be obtained from
     * the /lexicalcategories/ endpoint. TODO: Not sure what this endpoint is
     *
     * The filter keeps all the entries in the response whose lexicalCategory "id" matches the values in the
     * lexicalCategory parameter.
     */
    val lexicalCategory: List<String> = listOf()

) : Query {

    /**
     * Get gets the parameters of the call as a map of strings
     */
    override fun parameters(): Map<String, String> {
        return mapOf(
            "grammaticalFeatures" to grammaticalFeatures.joinToString(","),
            "lexicalCategory" to lexicalCategory.joinToString(","),
        ).filterValues { it.isNotEmpty() };
    }

    /**
     * Get the url path fragment for the call
     */
    override fun pathFragment(): String {
        return "lemmas/${sourceLanguage.value}/$word"
    }
}

