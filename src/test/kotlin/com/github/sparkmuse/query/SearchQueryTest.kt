package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.search.SearchQuery
import com.github.sparkmuse.query.search.SearchThesaurusQuery
import com.github.sparkmuse.query.search.SearchTranslationsQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SearchQueryTest {

    @Nested
    inner class SearchTranslations {

        @TestWithFixture
        fun `parameters gets all query parameters as a map`(query: SearchTranslationsQuery) {

            val expected = mapOf(
                "q" to query.q,
                "prefix" to query.prefix.toString(),
                "limit" to query.limit.toString(),
                "offset" to query.offset.toString(),
            )

            val actual = query.parameters

            assertThat(actual).containsAllEntriesOf(expected)
        }

        @Test
        fun pathFragment() {
            val query = SearchTranslationsQuery("ace", LanguageBilingual.English, LanguageBilingual.Spanish)
            val actual = query.fragments
            assertThat(actual).containsExactly("search", "translations", "en", "es")
        }
    }

    @Nested
    inner class Search {

        @TestWithFixture
        fun `parameters gets all query parameters as a map`(query: SearchQuery) {

            val expected = mapOf(
                "q" to query.q,
                "prefix" to query.prefix.toString(),
                "limit" to query.limit.toString(),
                "offset" to query.offset.toString(),
            )

            val actual = query.parameters

            assertThat(actual).containsExactlyEntriesOf(expected)
        }

        @Test
        fun pathFragment() {
            val query = SearchQuery("ace", LanguageMonolingual.English_gb)
            val actual = query.fragments
            assertThat(actual).containsExactly("search", "en-gb")
        }
    }

    @Nested
    inner class SearchThesaurus {

        @TestWithFixture
        fun `parameters gets all query parameters as a map`(query: SearchThesaurusQuery) {

            val expected = mapOf(
                "q" to query.q,
                "prefix" to query.prefix.toString(),
                "limit" to query.limit.toString(),
                "offset" to query.offset.toString(),
            )

            val actual = query.parameters

            assertThat(actual).containsExactlyEntriesOf(expected)
        }

        @Test
        fun pathFragment() {
            val query = SearchThesaurusQuery("ace", LanguageThesaurus.English)
            val actual = query.fragments
            assertThat(actual).containsExactly("search", "thesaurus", "en")
        }
    }
}