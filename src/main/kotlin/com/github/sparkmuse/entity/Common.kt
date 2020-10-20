package com.github.sparkmuse.entity

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Common classes for the entities.
 *
 * https://developer.oxforddictionaries.com/documentation
 * https://developer.oxforddictionaries.com/documentation/glossary
 *
 */

data class GrammaticalFeature(
    val id: String = "",
    val text: String = "",
    val type: String = ""
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
    val id: String = "",
    val text: String = "",
    val type: String = ""
)

data class VariantForm(
    val domains: List<Domain> = listOf(),
    val notes: List<CategorizedText> = listOf(),
    val pronunciations: List<Pronunciation> = listOf(),
    val regions: List<Region> = listOf(),
    val registers: List<Register> = listOf(),
    val text: String = ""
)

data class Pronunciation(
    val audioFile: String = "",
    val dialects: List<String> = listOf(),
    val phoneticNotation: String = "",
    val phoneticSpelling: String = "",
    val regions: List<Region> = listOf(),
    val registers: List<Register> = listOf()

)

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
    val variantForms: List<VariantForm> = listOf()
) {
    data class Construction(
        val domains: List<Domain> = listOf(),
        val examples: List<List<String>> = listOf(),
        val notes: List<CategorizedText> = listOf(),
        val regions: List<Region> = listOf(),
        val registers: List<Register> = listOf(),
        val text: String
    )

    data class AntonymSynonym(
        val id: String = "",
        val domains: List<Domain> = listOf(),
        val language: String = "",
        val regions: List<Region> = listOf(),
        val registers: List<Register> = listOf(),
        val text: String = ""
    )

    data class Example(
        val definitions: List<String> = listOf(),
        val domains: List<Domain> = listOf(),
        val notes: List<CategorizedText> = listOf(),
        val regions: List<Region> = listOf(),
        val registers: List<Register> = listOf(),
        val senseIds: List<String> = listOf(),
        val text: String = ""
    )
}

data class LexicalEntry(
    val compounds: List<RelatedEntry> = listOf(),
    val derivativeOf: List<RelatedEntry> = listOf(),
    val derivatives: List<RelatedEntry> = listOf(),
    val entries: List<Entry> = listOf(),
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
    data class Entry(
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
    )
}

data class RelatedEntry(
    val id: String,
    val domains: List<Domain> = listOf(),
    val language: String = "",
    val regions: List<Region> = listOf(),
    val registers: List<Register> = listOf(),
    val text: String
)

data class CrossReference(
    val id: String,
    val text: String,
    val type: String
)

data class ThesaurusLink(
    @JsonProperty("entry_id")
    val entryId: String,
    @JsonProperty("sense_id")
    val senseId: String
)

data class Inflection(
    val domains: List<Domain> = listOf(),
    val grammaticalFeatures: List<GrammaticalFeature> = listOf(),
    val inflectedForm: String = "",
    val lexicalCategory: LexicalCategory = LexicalCategory(),
    val pronunciations: List<Pronunciation> = listOf(),
    val regions: List<Region> = listOf(),
    val registers: List<Register> = listOf()
)
