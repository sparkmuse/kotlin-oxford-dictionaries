package com.github.sparkmuse

import com.github.sparkmuse.entity.*
import com.github.sparkmuse.internal.HttpClient
import com.github.sparkmuse.internal.parse
import com.github.sparkmuse.query.EntryQuery
import com.github.sparkmuse.query.ThesaurusQuery
import com.github.sparkmuse.query.LemmaQuery
import com.github.sparkmuse.query.TranslationQuery
import com.github.sparkmuse.query.search.SearchQuery
import com.github.sparkmuse.query.search.SearchThesaurusQuery
import com.github.sparkmuse.query.search.SearchTranslationsQuery

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
}

