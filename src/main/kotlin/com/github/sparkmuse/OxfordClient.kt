package com.github.sparkmuse

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sparkmuse.entity.*
import com.github.sparkmuse.query.*
import com.github.sparkmuse.query.search.SearchQuery
import com.github.sparkmuse.query.search.SearchThesaurusQuery
import com.github.sparkmuse.query.search.SearchTranslationsQuery
import com.github.sparkmuse.query.utility.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration
import java.time.temporal.ChronoUnit.SECONDS

class OxfordClient(
        val appId: String,
        val appKey: String,
        val baseUrl: String = "https://od-api.oxforddictionaries.com/api/v2"
) {

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
    fun entries(query: EntryQuery): RetrieveEntry = execute(query)

    /**
     * @see OxfordClient.entries
     */
    fun entries(word: String): RetrieveEntry = entries(EntryQuery(word = word))

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
    fun lemmas(query: LemmaQuery): Lemmatron = execute(query)

    /**
     * @see OxfordClient.lemmas
     */
    fun lemmas(word: String): Lemmatron = lemmas(LemmaQuery(word = word))

    /**
     * /search/translations/{source_lang_search}/{target_lang_search}:
     *
     * Use this to find possible translations for a given word.
     */
    fun searchTranslations(query: SearchTranslationsQuery): WordList = execute(query)

    /**
     * @see OxfordClient.searchTranslations
     */
    fun searchTranslations(query: String): WordList = searchTranslations(SearchTranslationsQuery(q = query))

    /**
     * /api/v2/search/{source_lang}:
     *
     * Use this to retrieve possible headword matches for a given string of text.
     * The results are calculated using headword matching, fuzzy matching, and lemmatization
     */
    fun search(query: SearchQuery): WordList = execute(query)

    /**
     * @see OxfordClient.search
     */
    fun search(query: String): WordList = search(SearchQuery(q = query))

    /**
     * /api/v2/search/thesaurus/{source_lang}:
     *
     * Use this to retrieve possible headword matches for a given string of text.
     * The results are calculated using headword matching, fuzzy matching, and lemmatization
     */
    fun searchThesaurus(query: SearchThesaurusQuery): WordList = execute(query)

    /**
     * @see OxfordClient.searchThesaurus
     */
    fun searchThesaurus(query: String): WordList = searchThesaurus(SearchThesaurusQuery(q = query))

    /**
     * /api/v2/search/thesaurus/{source_lang}:
     *
     * Use this to retrieve possible headword matches for a given string of text.
     * The results are calculated using headword matching, fuzzy matching, and lemmatization
     */
    fun thesaurus(query: ThesaurusQuery): Thesaurus = execute(query)

    /**
     * @see OxfordClient.thesaurus
     */
    fun thesaurus(word: String): Thesaurus = thesaurus(ThesaurusQuery(word = word))

    /**
     * /api/v2/translations/{source_lang_translate}/{target_lang_translate}/{word_id}:
     *
     * Use this to return translations for a given word. In the event that a word in the dataset
     * does not have a direct translation, the response will be a definition in the target language.
     */
    fun translations(query: TranslationQuery): RetrieveTranslation = execute(query)

    /**
     * @see OxfordClient.translations
     */
    fun translations(word: String): RetrieveTranslation = translations(TranslationQuery(word = word))

    /**
     * /api/v2/sentences/{source_lang}/{word_id}:
     *
     * Use this to retrieve sentences extracted from a corpus of real-world language, including news and blog content.
     * This is available for English and Spanish. For English, the sentences are linked to the correct sense of the
     * word in the dictionary. In Spanish, they are linked at the headword level.
     */
    fun sentences(query: SentenceQuery): SentencesResults = execute(query)

    /**
     * @see OxfordClient.sentences
     */
    fun sentences(word: String): SentencesResults = sentences(SentenceQuery(word = word))

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
    fun words(query: WordQuery): RetrieveEntry = execute(query)

    /**
     * @see OxfordClient.words
     */
    fun words(query: String): RetrieveEntry = words(WordQuery(q = query))

    /**
     * /inflections/{source_lang}/{word_id}:
     * Retrieve all the inflections of a given word_id. The inflections are given for a lexicalEntry with a
     * specific lexicalCategory.
     */
    fun inflections(query: InflectionQuery): RetrieveInflection = execute(query)

    /**
     * @see OxfordClient.inflections
     */
    fun inflections(word: String): RetrieveInflection = inflections(InflectionQuery(word = word))

    /**
     * /domains/{source_lang}:
     * Lists available domains in a monolingual dataset
     */
    fun domains(query: DomainMonolingualQuery): RetrieveDomain = execute(query)

    /**
     * /domains/{source_lang_domains}/{target_lang_domains}:
     * Lists available domains in a bilingual dataset
     */
    fun domains(query: DomainBilingualQuery): RetrieveDomain = execute(query)

    /**
     * /fields:
     * Lists all available fields
     */
    fun fields(query: FieldQuery): RetrieveField = execute(query)

    /**
     * /fields/{endpoint}:
     * Lists available fields for specific endpoint
     */
    fun fields(query: FieldEndpointQuery): RetrieveField = execute(query)

    /**
     * /filters:
     * Lists all available filters
     */
    fun filters(query: FilterQuery): RetrieveFilter = execute(query)

    /**
     * /filters/{endpoint}:
     * Lists available filters for specific endpoint
     */
    fun filters(query: FilterEndpointQuery): RetrieveFilter = execute(query)

    /**
     * /grammaticalFeatures/{source_lang}:
     * Lists available grammatical features in a monolingual dataset
     */
    fun grammaticalFeatures(query: GrammaticalFeatureMonolingualQuery): RetrieveGrammaticalFeature = execute(query)

    /**
     * /grammaticalFeatures/{source_lang_grammatical}/{target_lang_grammatical}:
     * Lists available grammatical features in a bilingual dataset
     */
    fun grammaticalFeatures(query: GrammaticalFeatureBilingualQuery): RetrieveGrammaticalFeature = execute(query)

    /**
     * /lexicalCategories/{source_lang}:
     * Lists available lexical categories in a monolingual dataset
     */
    fun lexicalCategories(query: LexicalCategoryMonolingualQuery): RetrieveLexicalCategory = execute(query)

    /**
     * /lexicalCategories/{source_lang_grammatical}/{target_lang_grammatical}:
     * Lists available lexical categories in a bilingual dataset
     */
    fun lexicalCategories(query: LexicalCategoryBilingualQuery): RetrieveLexicalCategory = execute(query)

    /**
     * /registers/{source_lang}:
     * Lists available registers in a monolingual dataset
     */
    fun registers(query: RegisterMonolingualQuery): RetrieveRegister = execute(query)

    /**
     * /register/{source_lang_grammatical}/{target_lang_grammatical}:
     * Lists available registers in a bilingual dataset
     */
    fun registers(query: RegisterBilingualQuery): RetrieveRegister = execute(query)

    /**
     * /languages:
     * Returns the names of Dictionaries in the API
     */
    fun languages(query: LanguageQuery): RetrieveLanguage = execute(query)


    internal inline fun <reified T> execute(query: Query): T {

        val url = createUri(query)

        val request: HttpRequest = HttpRequest.newBuilder()
                .uri(url)
                .header("Accept", "application/json")
                .header("app_id", appId)
                .header("app_key", appKey)
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build()

        val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())

        return when (response.statusCode()) {
            200 -> jacksonObjectMapper().readValue(response.body(), T::class.java)
            else -> throw OxfordClientException("StatusCode: ${response.statusCode()} Error: ${response.body()}")
        }
    }

    class OxfordClientException(message: String) : Exception(message)

    private fun createUri(query: Query): URI {
        return URI("$baseUrl//${query.pathFragment}?${query.queryParams}").normalize()
    }
}