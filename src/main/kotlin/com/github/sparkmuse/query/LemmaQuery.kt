package com.github.sparkmuse.query

class LemmaQuery(

    /**
     * The identifier for an Entry (case-sensitive)
     */
    val word: String,

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: LanguageMonolingual = LanguageMonolingual.English_gb,

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
    val lexicalCategory: List<String> = listOf()

) : Query {

    override val queryParams: String
        get() {
            return mapOf(
                "grammaticalFeatures" to grammaticalFeatures.joinWithComma(),
                "lexicalCategory" to lexicalCategory.joinWithComma(),
            )
                .filterValues { it.isNotEmpty() }
                .joinWithAmpersand()
        }

    override val pathFragment: String
        get() = "lemmas/${sourceLanguage.value}/$word"
}

