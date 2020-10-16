package com.github.sparkmuse.lemmas

data class Lemmatron(

    /**
     *  Additional Information provided by OUP (Optional)
     */
    val metadata: Map<String, String> = mapOf(),

    /**
     * A list of inflections matching a given word (Optional)
     */
    val results: List<HeadwordLemmatron> = listOf()
)

data class HeadwordLemmatron(

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
    val lexicalEntries: List<LexicalEntry>,

    /**
     * The json object type. Could be 'headword', 'inflection' or 'phrase' (Optional)
     */
    val type: String = "",

    /**
     * A given written or spoken realisation of an entry, lowercased. (Deprecated, Optional)
     */
    val word: String = ""
)


data class LexicalEntry(

    /**
     * GrammaticalFeatures (Optional)
     */
    val grammaticalFeatures: List<GrammaticalFeature> = listOf(),

    /**
     * The canonical form of words for which the entry is an inflection
     */
    val inflectionOf: List<Inflection>,

    /**
     * IANA language code
     * https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
     */
    val language: String,

    /**
     * A linguistic category of words (or more precisely lexical items), generally defined by the syntactic or
     * morphological behaviour of the lexical item in question, such as noun or verb
     */
    val lexicalCategory: LexicalCategory,

    /**
     *  A given written or spoken realisation of an entry
     */
    val text: String
)

class GrammaticalFeature(
    val id: String,
    val text: String,
    val type: String,
)

data class Inflection(
    val id: String,
    val text: String
)

data class LexicalCategory(
    val id: String,
    val text: String
)