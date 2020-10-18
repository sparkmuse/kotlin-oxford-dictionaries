package com.github.sparkmuse

import com.github.sparkmuse.entity.*
import com.github.sparkmuse.entity.utility.*
import com.github.sparkmuse.internal.HttpClient
import com.github.sparkmuse.internal.parse
import com.github.sparkmuse.query.*
import com.github.sparkmuse.query.search.SearchQuery
import com.github.sparkmuse.query.search.SearchThesaurusQuery
import com.github.sparkmuse.query.search.SearchTranslationsQuery
import com.github.sparkmuse.query.utility.*

class OxfordClient(
    val appId: String,
    val appKey: String,
    val baseUrl: String = "https://od-api.oxforddictionaries.com/api/v2"
) {

    private val httpClient = HttpClient(appId, appKey, baseUrl)

    /**
     * /api/v2/entries/{source_lang}/{word_id}:
     *
     * Use this to retrieve definitions, pronunciations, example sentences, grammatical information and word origins
     *
     * TIP: Entries ONLY works for dictionary headwords. You may need to use the Lemmas endpoint first to link an
     * inflected form back to its headword (e.g., pixels --> pixel).
     *
     * Use filters to limit the entry information that is returned.
     * For example, you may only require definitions and not everything else, or just pronunciations
     *
     * The full list of filters can be retrieved from the filters Utility endpoint.
     * You can also specify values within the filter using '='. For example 'grammaticalFeatures=singular'.
     *
     * Filters can also be combined.
     * Combining different filters will build a query using 'AND' operators, while if a filter contains more
     * than one value it will build a query using 'OR' operators. For example, a combination of filters
     * like '?grammaticalFeatures=singular&lexicalCategory=noun,verb' will return entries which match the
     * query ('noun' OR 'verb') AND 'singular'.
     */
    fun entries(query: EntryQuery): RetrieveEntry? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.entries
     */
    fun entries(word: String): RetrieveEntry? {
        return this.entries(EntryQuery(word = word))
    }

    /**
     * /api/v2/lemmas/{source_lang}/{word_id}:
     *
     * Use this to check if a word exists in the dictionary, or what 'root' form it links to (e.g., swimming > swim).
     *
     * The response tells you the possible lemmas for a given inflected word. This can then be combined with other
     * endpoints to retrieve more information.
     *
     * The results can be filtered by lexicalCategories and/or grammaticalFeatures. Filters can be combined.
     * Combining different filters will build a query using 'AND' operators, while if a filter contains more than
     * one value it will build a query using 'OR' operators. For example, a combination of filters
     * like '?grammaticalFeatures=singular&lexicalCategory=noun,verb' will return entries which match
     * the query ('noun' OR 'verb') AND 'singular'.
     */
    fun lemmas(query: LemmaQuery): Lemmatron? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.lemmas
     */
    fun lemmas(word: String): Lemmatron? {
        return this.lemmas(LemmaQuery(word = word))
    }

    /**
     * /search/translations/{source_lang_search}/{target_lang_search}:
     *
     * Use this to find possible translations for a given word.
     */
    fun searchTranslations(query: SearchTranslationsQuery): WordList? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.searchTranslations
     */
    fun searchTranslations(query: String): WordList? {
        return this.searchTranslations(SearchTranslationsQuery(q = query))
    }

    /**
     * /api/v2/search/{source_lang}:
     *
     * Use this to retrieve possible headword matches for a given string of text.
     * The results are calculated using headword matching, fuzzy matching, and lemmatization
     */
    fun search(query: SearchQuery): WordList? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.search
     */
    fun search(query: String): WordList? {
        return this.search(SearchQuery(q = query))
    }

    /**
     * /api/v2/search/thesaurus/{source_lang}:
     *
     * Use this to retrieve possible headword matches for a given string of text.
     * The results are calculated using headword matching, fuzzy matching, and lemmatization
     */
    fun searchThesaurus(query: SearchThesaurusQuery): WordList? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.searchThesaurus
     */
    fun searchThesaurus(query: String): WordList? {
        return this.searchThesaurus(SearchThesaurusQuery(q = query))
    }

    /**
     * /api/v2/search/thesaurus/{source_lang}:
     *
     * Use this to retrieve possible headword matches for a given string of text.
     * The results are calculated using headword matching, fuzzy matching, and lemmatization
     */
    fun thesaurus(query: ThesaurusQuery): Thesaurus? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.thesaurus
     */
    fun thesaurus(word: String): Thesaurus? {
        return this.thesaurus(ThesaurusQuery(word = word))
    }

    /**
     * /api/v2/translations/{source_lang_translate}/{target_lang_translate}/{word_id}:
     *
     * Use this to return translations for a given word. In the event that a word in the dataset
     * does not have a direct translation, the response will be a definition in the target language.
     */
    fun translations(query: TranslationQuery): RetrieveTranslation? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.translations
     */
    fun translations(word: String): RetrieveTranslation? {
        return this.translations(TranslationQuery(word = word))
    }

    /**
     * /api/v2/sentences/{source_lang}/{word_id}:
     *
     * Use this to retrieve sentences extracted from a corpus of real-world language, including news and blog content.
     * This is available for English and Spanish. For English, the sentences are linked to the correct sense of the
     * word in the dictionary. In Spanish, they are linked at the headword level.
     */
    fun sentences(query: SentenceQuery): SentencesResults? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.sentences
     */
    fun sentences(word: String): SentencesResults? {
        return this.sentences(SentenceQuery(word = word))
    }

    /**
     * /api/v2/words/{source_lang}:
     *
     * Use this endpoint to retrieve definitions, examples and other information for a given dictionary word or
     * an inflection (e.g., running > run). The response contains information about the lemmas to which the
     * given word/inflected form is linked.
     *
     * The results can be filtered by lexicalCategories, domains, registers or grammaticalFeatures.
     * Filters can be combined.
     *
     * In addition, users can use fields to project some of the properties.
     * Combining different filters will build a query using 'AND' operators, while if a filter contains more than
     * one value it will build a query using 'OR' operators. For example, a combination of filters
     * like '?grammaticalFeatures=singular&lexicalCategory=noun,verb' will return entries which match the query
     * ('noun' OR 'verb') AND 'singular'.
     */
    fun words(query: WordQuery): RetrieveEntry? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.words
     */
    fun words(word: String): RetrieveEntry? {
        return this.words(WordQuery(q = word))
    }

    /**
     * /domains/{source_lang}:
     * Lists available domains in a monolingual dataset
     */
    fun domain(query: DomainMonolingualQuery): RetrieveDomain? {
        return parse(httpClient.execute(query))
    }

    /**
     * /domains/{source_lang_domains}/{target_lang_domains}:
     * Lists available domains in a bilingual dataset
     */
    fun domain(query: DomainBilingualQuery): RetrieveDomain? {
        return parse(httpClient.execute(query))
    }

    /**
     * /fields:
     * Lists all available fields
     */
    fun field(query: FieldQuery): RetrieveField? {
        return parse(httpClient.execute(query))
    }

    /**
     * /fields/{endpoint}:
     * Lists available fields for specific endpoint
     */
    fun field(query: FieldEndpointQuery): RetrieveField? {
        return parse(httpClient.execute(query))
    }

    /**
     * /filters:
     * Lists all available filters
     */
    fun filter(query: FilterQuery): RetrieveFilter? {
        return parse(httpClient.execute(query))
    }

    /**
     * /filters/{endpoint}:
     * Lists available filters for specific endpoint
     */
    fun filter(query: FilterEndpointQuery): RetrieveFilter? {
        return parse(httpClient.execute(query))
    }

    /**
     * /grammaticalFeatures/{source_lang}:
     * Lists available grammatical features in a monolingual dataset
     */
    fun grammaticalFeature(query: GrammaticalFeatureMonolingualQuery): RetrieveGrammaticalFeature? {
        return parse(httpClient.execute(query))
    }

    /**
     * /grammaticalFeatures/{source_lang_grammatical}/{target_lang_grammatical}:
     * Lists available grammatical features in a bilingual dataset
     */
    fun grammaticalFeature(query: GrammaticalFeatureBilingualQuery): RetrieveGrammaticalFeature? {
        return parse(httpClient.execute(query))
    }

    /**
     * /lexicalCategories/{source_lang}:
     * Lists available lexical categories in a monolingual dataset
     */
    fun lexicalCategory(query: LexicalCategoryMonolingualQuery): RetrieveLexicalCategory? {
        return parse(httpClient.execute(query))
    }

    /**
     * /lexicalCategories/{source_lang_grammatical}/{target_lang_grammatical}:
     * Lists available lexical categories in a bilingual dataset
     */
    fun lexicalCategory(query: LexicalCategoryBilingualQuery): RetrieveLexicalCategory? {
        return parse(httpClient.execute(query))
    }
}

