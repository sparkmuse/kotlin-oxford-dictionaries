package com.github.sparkmuse.entity

data class Thesaurus(

    /**
     * Additional Information provided by OUP (Optional)
     */
    val metadata: Map<String, String> = mapOf(),

    /**
     *  A list of found synonyms or antonyms (Optional)
     */
    val results: List<HeadwordThesaurus> = listOf()
) {
    data class HeadwordThesaurus(

        /**
         * The identifier of a word
         */
        val id: String = "",

        /**
         * IANA language code
         */
        val language: String = "",

        /**
         * A grouping of various senses in a specific language, and a lexical category that relates to a word
         */
        val lexicalEntries: List<LexicalEntry> = listOf(),

        /**
         * The json object type. Could be 'headword', 'inflection' or 'phrase'
         */
        val type: String = "",

        /**
         * A given written or spoken realisation of an entry, lowercased. (Deprecated)
         */
        val word: String = ""
    )
}