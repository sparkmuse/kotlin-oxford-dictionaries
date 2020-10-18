package com.github.sparkmuse.entity

import com.fasterxml.jackson.annotation.JsonProperty

data class RetrieveEntry(

    /**
     * Id
     */
    val id: String,

    /**
     *  Additional Information provided by OUP
     */
    val metadata: Map<String, String> = mapOf(),

    /**
     * A list of entries and all the data related to them (Optional)
     */
    val results: List<HeadwordEntry> = listOf(),

    /**
     * Word (Optional)
     */
    val word: String = ""
)

data class HeadwordEntry(

    /**
     * The identifier of a word
     */
    val id: String,

    /**
     * IANA language code
     * https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
     */
    val language: String,

    /**
     * A grouping of various senses in a specific language, and a lexical category that relates to a word
     */
    val lexicalEntries: List<LexicalEntry> = listOf(),

    /**
     * A grouping of pronunciation information (Optional)
     */
    val pronunciations: List<Pronunciation> = listOf(),

    /**
     * The json object type. Could be 'headword', 'inflection' or 'phrase' (Optional)
     */
    val type: String = "",

    /**
     * A given written or spoken realisation of an entry, lowercased. (Deprecated, Optional)
     */
    val word: String = ""
)

data class RelatedEntry(

    /**
     * The identifier of the word
     */
    val id: String,

    /**
     * A subject, discipline, or branch of knowledge particular to the Sense (Optional)
     */
    val domains: List<Domain> = listOf(),

    /**
     * IANA language code
     * https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
     */
    val language: String = "",

    /**
     * A particular area in which the variant form occurs, e.g. 'Great Britain' (Optional)
     */
    val regions: List<Region> = listOf(),

    /**
     * A level of language usage, typically with respect to formality. e.g. 'offensive', 'informal' (Optional)
     */
    val registers: List<Register> = listOf(),

    /**
     * Text
     */
    val text: String
)

data class Construction(

    /**
     * (Optional)
     */
    val domains: List<Domain> = listOf(),

    /**
     * (Optional)
     */
    val examples: List<List<String>> = listOf(),

    /**
     * (Optional)
     */
    val notes: List<CategorizedText> = listOf(),

    /**
     * (Optional)
     */
    val regions: List<Region> = listOf(),

    /**
     * (Optional)
     */
    val registers: List<Register> = listOf(),

    /**
     * The construction text
     */
    val text: String
)