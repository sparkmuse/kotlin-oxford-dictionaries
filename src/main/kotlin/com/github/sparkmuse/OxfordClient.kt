package com.github.sparkmuse

import com.github.sparkmuse.entity.Lemmatron
import com.github.sparkmuse.entity.RetrieveEntry
import com.github.sparkmuse.entity.search.WordList
import com.github.sparkmuse.internal.HttpClient
import com.github.sparkmuse.internal.parse
import com.github.sparkmuse.query.EntryQuery
import com.github.sparkmuse.query.LemmaQuery
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
        return entries(EntryQuery(word))
    }

    /**
     * /api/v2/lemmas/{source_lang}/{word_id}:
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
        return lemmas(LemmaQuery(word))
    }

    /**
     * /search/translations/{source_lang_search}/{target_lang_search}:
     * Use this to find possible translations for a given word.
     */
    fun searchTranslations(query: SearchTranslationsQuery): WordList? {
        return parse(httpClient.execute(query))
    }

    /**
     * @see OxfordClient.searchTranslations
     */
    fun searchTranslations(query: String): WordList? {
        return searchTranslations(SearchTranslationsQuery(query))
    }

    /**
     * /api/v2/search/{source_lang}:
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
        return search(SearchQuery(query))
    }

    /**
     * /api/v2/search/thesaurus/{source_lang}:
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
        return searchThesaurus(SearchThesaurusQuery(query))
    }
}

