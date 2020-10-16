package com.github.sparkmuse.entity.search

data class Wordlist(

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
     * A given written or spoken realisation of an entry, lowercased. (Deprecated, Optional)
     */
    val word: String = ""
)