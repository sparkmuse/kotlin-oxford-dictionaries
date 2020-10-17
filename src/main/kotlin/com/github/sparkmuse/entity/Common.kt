package com.github.sparkmuse.entity

data class GrammaticalFeature(
    val id: String = "",
    val text: String = "",
    val type: String = "",
)

data class LexicalCategory(
    val id: String = "",
    val text: String = ""
)

data class Domain(
    val id: String = "",
    val text: String = ""
)

data class DomainClass(
    val id: String = "",
    val text: String = ""
)

data class Region(
    val id: String = "",
    val text: String = ""
)

data class Register(
    val id: String = "",
    val text: String = ""
)

data class SemanticClass(
    val id: String = "",
    val text: String = ""
)

data class CategorizedText(

    /**
     * The identifier of the word (Optional)
     */
    val id: String = "",

    /**
     *  A note text
     */
    val text: String = "",

    /**
     * The descriptive category of the text
     */
    val type: String = ""
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
    val text: String = ""
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
    val text: String = ""
)

data class AntonymSynonym(

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
    val text: String = ""
)

data class Sense(

    /**
     * Antonym of word (Optional)
     */
    val antonyms: List<AntonymSynonym> = listOf(),

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
    val synonyms: List<AntonymSynonym> = listOf(),

    /**
     *  Ordered list of links to the Thesaurus Dictionary (Optional)
     */
    val thesaurusLinks: List<ThesaurusLink> = listOf(),

    /**
     * Various words that are used interchangeably depending on the context, e.g 'duck' and 'duck boat' (Optional)
     */
    val variantForms: List<VariantForm> = listOf()
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
    val language: String = "",

    /**
     * A linguistic category of words (or more precisely lexical items), generally defined by the syntactic or
     * morphological behaviour of the lexical item in question, such as noun or verb
     */
    val lexicalCategory: LexicalCategory = LexicalCategory(),

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
    val text: String = "",

    /**
     * Various words that are used interchangeably depending on the context, e.g 'a' and 'an' (Optional)
     */
    val variantForms: List<VariantForm> = listOf()
)