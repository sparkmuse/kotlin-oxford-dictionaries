package com.github.sparkmuse

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
    val type: HeadwordEntryType,

    /**
     * A given written or spoken realisation of an entry, lowercased. (Deprecated, Optional)
     */
    val word: String = ""
)

enum class HeadwordEntryType { headword, inflecction, phrase }

data class LexicalEntry(

    /**
     * Other words from which their Sense derives (Optional)
     */
    val compounds: List<RelatedEntry> = listOf(),

    /**
     * Other words from which this one derives (Optional)
     */
    val derivativeOf: List<RelatedEntry> = listOf(),

    /**
     * Other words from which their Sense derives (Optional)
     */
    val derivatives: List<RelatedEntry> = listOf(),

    /**
     * Entries. (Optional)
     */
    val entries: List<Entry> = listOf(),

    /**
     * GrammaticalFeature. (Optional)
     */
    val grammaticalFeatures: List<GrammaticalFeature> = listOf(),

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
     * Notes (Optional)
     */
    val notes: List<CategorizedText> = listOf(),


    /**
     * Other words from which their Sense derives (Optional)
     */
    val phrasalVerbs: List<RelatedEntry> = listOf(),

    /**
     * Other words from which their Sense derive (Optional)
     */
    val phrases: List<RelatedEntry> = listOf(),

    /**
     * Pronunciation (Optional)
     */
    val pronunciations: List<Pronunciation> = listOf(),

    /**
     * Abstract root form from which this lexicalEntry is derived (Optional)
     */
    val root: String = "",

    /**
     * A given written or spoken realisation of an entry
     */
    val text: String,

    /**
     * Various words that are used interchangeably depending on the context, e.g 'a' and 'an' (Optional)
     */
    val variantForms: List<VariantForm> = listOf()
)

data class VariantForm(

    /**
     * A subject, discipline, or branch of knowledge particular to the Sense (Optional)
     */
    val domains: List<Domain> = listOf(),

    /**
     * Notes (Optional)
     */
    val notes: List<CategorizedText> = listOf(),

    /**
     * A grouping of pronunciation information (Optional)
     */
    val pronunciations: List<Pronunciation> = listOf(),

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

data class Domain(

    val id: String,
    val text: String
)

data class Region(

    val id: String,
    val text: String
)

data class Register(

    val id: String,
    val text: String
)

data class CategorizedText(

    /**
     * The identifier of the word (Optional)
     */
    val id: String = "",

    /**
     *  A note text
     */
    val text: String,

    /**
     * The descriptive category of the text
     */
    val type: String
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

data class GrammaticalFeature(

    val id: String,
    val text: String,
    val type: String
)

data class LexicalCategory(
    val id: String,
    val text: String
)

data class Entry(

    /**
     * A grouping of crossreference notes (Optional)
     */
    val crossReferenceMarkers: List<String> = listOf(),

    /**
     * CrossReferencesList (Optional)
     */
    val crossReferences: List<CrossReference> = listOf(),

    /**
     * The origin of the word and the way in which its meaning has changed throughout history (Optional)
     */
    val etymologies: List<String> = listOf(),

    /**
     * GrammaticalFeatures (Optional)
     */
    val grammaticalFeatures: List<GrammaticalFeature> = listOf(),

    /**
     * Identifies the homograph grouping. The last two digits identify different entries of the same homograph.
     * The first one/two digits identify the homograph number. (Optional)
     */
    val homographNumber: String = "",

    /**
     *  A list of inflected forms for an Entry (Optional)
     */
    val inflections: List<Inflection> = listOf(),

    /**
     * Notes (Optional)
     */
    val notes: List<CategorizedText> = listOf(),

    /**
     * Pronunciations (Optional)
     */
    val pronunciations: List<Pronunciation> = listOf(),

    /**
     * Complete list of senses (Optional)
     */
    val senses: List<Sense> = listOf(),

    /**
     * Various words that are used interchangeably depending on the context, e.g 'a' and 'an' (Optional)
     */
    val variantForms: List<VariantForm> = listOf()
)

data class Sense(

    /**
     * Antonym of word (Optional)
     */
    val antonyms: List<SynonymsAntonyms> = listOf(),

    /**
     * A construction provides information about typical syntax used of this sense.
     * Each construction may optionally have one or more examples. (Optional)
     */
    val constructions: List<Construction> = listOf(),

    /**
     *  A grouping of crossreference notes (Optional)
     */
    val crossReferenceMarkers: List<String> = listOf(),

    /**
     * CrossReferencesList (Optional)
     */
    val crossReferences: List<CrossReference> = listOf(),

    /**
     *  A list of statements of the exact meaning of a word (Optional)
     */
    val definitions: List<String> = listOf(),

    /**
     *  Domain classes particular to the Sense (Optional)
     */
    val domainClasses: List<DomainClass> = listOf(),

    /**
     * A subject, discipline, or branch of knowledge particular to the Sense (Optional)
     */
    val domains: List<Domain> = listOf(),

    /**
     * The origin of the word and the way in which its meaning has changed throughout history (Optional)
     */
    val etymologies: List<String> = listOf(),

    /**
     * ExamplesList (Optional)
     */
    val examples: List<Example> = listOf(),

    /**
     * The id of the sense that is required for the delete procedure (Optional)
     */
    val id: String = "",

    /**
     *  A list of inflected forms for a sense (Optional)
     */
    val inflections: List<Inflection> = listOf(),

    /**
     * Notes (Optional)
     */
    val notes: List<CategorizedText> = listOf(),

    /**
     * PronunciationsList (Optional)
     */
    val pronunciations: List<Pronunciation> = listOf(),

    /**
     * A particular area in which the Sense occurs, e.g. 'Great Britain' (Optional)
     */
    val regions: List<Region> = listOf(),

    /**
     * A level of language usage, typically with respect to formality. e.g. 'offensive', 'informal' (Optional)
     */
    val registers: List<Register> = listOf(),

    /**
     * Semantic classes particular to the Sense (Optional)
     */
    val semanticClasses: List<SemanticClass> = listOf(),

    /**
     * A list of short statements of the exact meaning of a word (Optional)
     */
    val shortDefinitions: List<String> = listOf(),

    /**
     * Ordered list of subsenses of a sense (Optional)
     */
    val subsenses: List<Sense> = listOf(),

    /**
     * Synonym of word (Optional)
     */
    val synonyms: List<SynonymsAntonyms> = listOf(),

    /**
     *  Ordered list of links to the Thesaurus Dictionary (Optional)
     */
    val thesaurusLinks: List<ThesaurusLink> = listOf(),

    /**
     * Various words that are used interchangeably depending on the context, e.g 'duck' and 'duck boat' (Optional)
     */
    val variantForms: List<VariantForm> = listOf()
)

data class ThesaurusLink(

    /**
     * Identifier of a word
     */
    @JsonProperty("entry_id")
    val entryId: String,

    /**
     * Identifier of a sense
     */
    @JsonProperty("sense_id")
    val senseId: String
)

data class SemanticClass(

    val id: String,
    val text: String
)

data class Example(

    /**
     * A list of statements of the exact meaning of a word (Optional)
     */
    val definitions: List<String> = listOf(),

    /**
     * A subject, discipline, or branch of knowledge particular to the Sense (Optional)
     */
    val domains: List<Domain> = listOf(),

    /**
     * Notes (Optional)
     */
    val notes: List<CategorizedText> = listOf(),

    /**
     * A particular area in which the pronunciation occurs, e.g. 'Great Britain' (Optional)
     */
    val regions: List<Region> = listOf(),

    /**
     * A level of language usage, typically with respect to formality. e.g. 'offensive', 'informal' (Optional)
     */
    val registers: List<Register> = listOf(),

    /**
     * The list of sense identifiers related to the example. Provided in the sentences endpoint only. (Optional)
     */
    val senseIds: List<String> = listOf(),

    /**
     * Text
     */
    val text: String
)

data class DomainClass(
    val id: String,
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

data class SynonymsAntonyms(

    /**
     * Id (Optional)
     */
    val id: String = "",

    /**
     * Domains (Optional)
     */
    val domains: List<Domain> = listOf(),

    /**
     * Language (Optional)
     */
    val language: String = "",

    /**
     *  A particular area in which the Sense occurs, e.g. 'Great Britain' (Optional)
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

data class Inflection(

    val domains: List<Domain> = listOf(),
    val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
    val inflectedForm: String,
    val lexicalCategory: LexicalCategory?,
    val pronunciations: List<Pronunciation> = listOf(),
    val regions: List<Region> = listOf(),
    val registers: List<Register> = listOf()
)

data class CrossReference(

    /**
     * The word id of the co-occurrence
     */
    val id: String,

    /**
     * The word of the co-occurrence
     */
    val text: String,

    /**
     * The type of relation between the two words.
     * Possible values are 'close match', 'related', 'see also', 'variant spelling', and 'abbreviation' in case of crossreferences,
     * or 'pre', 'post' in case of collocates.
     */
    val type: String
)

data class Pronunciation(

    /**
     * The URL of the sound file (Optional)
     */
    val audioFile: String = "",

    /**
     * A local or regional variation where the pronunciation occurs, e.g. 'British English' (Optional)
     */
    val dialects: List<String> = listOf(),

    /**
     * The alphabetic system used to display the phonetic spelling (Optional)
     */
    val phoneticNotation: String = "",

    /**
     * Phonetic spelling is the representation of vocal sounds which express pronunciations of words.
     * It is a system of spelling in which each letter represents invariably the same spoken sound. (Optional)
     */
    val phoneticSpelling: String = "",

    /**
     * A particular area in which the pronunciation occurs, e.g. 'Great Britain' (Optional)
     */
    val regions: List<Region> = listOf(),

    /**
     * A level of language usage, typically with respect to formality. e.g. 'offensive', 'informal' (Optional)
     */
    val registers: List<Register> = listOf()

)