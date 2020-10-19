package com.github.sparkmuse.entity

/**
 * Use this to return translations for a given word. In the event that a word in the dataset does not have a direct
 * translation, the response will be a definition in the target language.
 *
 * https://developer.oxforddictionaries.com/documentation#!/Translations/get_translations_source_lang_translate_target_lang_translate_word_id
 * https://developer.oxforddictionaries.com/documentation/glossary
 *
 */
data class RetrieveTranslation(
    val metadata: Map<String, String> = mapOf(),
    val results: List<BilingualHeadwordEntry> = listOf()
) {
    data class BilingualHeadwordEntry(
        val id: String = "",
        val language: String = "",
        val lexicalEntries: List<BilingualLexicalEntry> = listOf(),
        val pronunciations: List<Pronunciation> = listOf(),
        val type: String = "",
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
                    data class AntonymSynonym(
                        val id: String = "",
                        val domains: List<Domain> = listOf(),
                        val language: String = "",
                        val regions: List<Region> = listOf(),
                        val registers: List<Register> = listOf(),
                        val text: String = ""
                    )

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
                }
            }
        }
    }
}

