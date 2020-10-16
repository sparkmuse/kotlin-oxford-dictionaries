package com.github.sparkmuse.entity.search

data class WordList(

    /**
     *  Additional Information provided by OUP (Optional)
     */
    val metadata: Map<String, String> = mapOf(),

    /**
     *  A list of found words (Optional)
     */
    val results: List<Results> = listOf()
)

data class Results(

    /**
     * The identifier of a word
     */
    val id: String,

    /**
     * Label (Optional)
     */
    val label: String = "",

    /**
     * MatchString (Optional)
     */
    val matchString: String = "",

    /**
     * MatchType (Optional)
     */
    val matchType: String = "",

    /**
     * Name of region. (Optional)
     */
    val region: String = "",

    /**
     * Score (Optional)
     */
    val score: Double = 0.0,

    /**
     * A given written or spoken realisation of an entry, lowercased. (Deprecated, Optional)
     */
    val word: String = ""
)