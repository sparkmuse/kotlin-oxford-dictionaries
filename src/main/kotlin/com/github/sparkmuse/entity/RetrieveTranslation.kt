package com.github.sparkmuse.entity

data class RetrieveTranslation(
    /**
     *  Additional Information provided by OUP (Optional)
     */
    val metadata: Map<String, String> = mapOf(),

    /**
     *  A list of bilingual entries and all the data related to them (Optional)
     */
    val results: List<BilingualHeadwordEntry> = listOf()
) {
    data class BilingualHeadwordEntry(
        /**
         * The identifier of a word
         */
        val id: String = "",

        /**
         *  IANA language code
         */
        val language: String = "",

        /**
         * A grouping of various senses containing translations in a specific language, and a lexical category that relates to a word
         */
        val lexicalEntries: List<BilingualLexicalEntry> = listOf(),

        /**
         * Pronunciation
         */
        val pronunciations: List<Pronunciation> = listOf(),

        /**
         *  The json object type. Could be 'headword', 'inflection' or 'phrase'
         */
        val type: String = "",

        /**
         * A given written or spoken realisation of an entry, lowercased
         */
        val word: String = ""
    ) {
        data class BilingualLexicalEntry(
            val compounds: List<RelatedEntry> = listOf(),
            val derivativeOf: List<RelatedEntry> = listOf(),
            val derivatives: List<RelatedEntry> = listOf(),
            val entries: List<BilingualEntry> = listOf(),
            val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
            val language: String = "",
            val lexicalCategory: LexicalCategory = LexicalCategory(),
            val notes: List<CategorizedText> = listOf(),
            val phrasalVerbs: List<RelatedEntry> = listOf(),
            val phrases: List<RelatedEntry> = listOf(),
            val pronunciations: List<Pronunciation> = listOf(),
            val root: String = "",
            val text: String = "",
            val variantForms: List<VariantForm> = listOf()
        ) {
            data class BilingualEntry(
                val crossReferenceMarkers: List<String> = listOf(),
                val crossReferences: List<CrossReference> = listOf(),
                val etymologies: List<String> = listOf(),
                val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
                val homographNumber: String = "",
                val inflections: List<Inflection> = listOf(),
                val notes: List<CategorizedText> = listOf(),
                val pronunciations: List<Pronunciation> = listOf(),
                val senses: List<Sense> = listOf(),
                val variantForms: List<VariantForm> = listOf()
            ) {
                data class Sense(
                    val antonyms: List<AntonymSynonym> = listOf(),
                    val constructions: List<Construction> = listOf(),
                    val crossReferenceMarkers: List<String> = listOf(),
                    val crossReferences: List<CrossReference> = listOf(),
                    val definitions: List<String> = listOf(),
                    val domainClasses: List<DomainClass> = listOf(),
                    val domains: List<Domain> = listOf(),
                    val etymologies: List<String> = listOf(),
                    val examples: List<Example> = listOf(),
                    val id: String = "",
                    val inflections: List<Inflection> = listOf(),
                    val notes: List<CategorizedText> = listOf(),
                    val pronunciations: List<Pronunciation> = listOf(),
                    val regions: List<Region> = listOf(),
                    val registers: List<Register> = listOf(),
                    val semanticClasses: List<SemanticClass> = listOf(),
                    val shortDefinitions: List<String> = listOf(),
                    val subsenses: List<Sense> = listOf(),
                    val synonyms: List<AntonymSynonym> = listOf(),
                    val thesaurusLinks: List<ThesaurusLink> = listOf(),
                    val translations: List<Translation> = listOf(),
                    val variantForms: List<VariantForm> = listOf()
                ) {
                    data class Construction(
                        val domains: List<Domain> = listOf(),
                        val examples: List<List<String>> = listOf(),
                        val notes: List<CategorizedText> = listOf(),
                        val regions: List<Region> = listOf(),
                        val registers: List<Register> = listOf(),
                        val text: String = "",
                        val translations: List<Translation> = listOf()
                    )

                    data class Example(
                        val collocations: List<CrossReference> = listOf(),
                        val crossReferenceMarkers: List<String> = listOf(),
                        val crossReferences: List<CrossReference> = listOf(),
                        val definitions: List<String> = listOf(),
                        val domains: List<Domain> = listOf(),
                        val notes: List<CategorizedText> = listOf(),
                        val regions: List<Region> = listOf(),
                        val registers: List<Register> = listOf(),
                        val senseIds: List<String> = listOf(),
                        val text: String = "",
                        val translations: List<Translation> = listOf()
                    )
                }
            }
        }
    }
}

data class Translation(
    val collocations: List<CrossReference> = listOf(),
    val domains: List<Domain> = listOf(),
    val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
    val language: String = "",
    val notes: List<CategorizedText> = listOf(),
    val regions: List<Region> = listOf(),
    val registers: List<Register> = listOf(),
    val text: String = "",
    val toneGroups: List<ToneGroup> = listOf(),
    val type: String = ""
) {

    data class ToneGroup(
        val tones: List<Tone> = listOf()
    ) {
        data class Tone(
            val type: String = "",
            val value: String = ""
        )
    }
}