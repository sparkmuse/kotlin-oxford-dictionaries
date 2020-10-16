package com.github.sparkmuse.query

import com.github.nylle.javafixture.Fixture
import com.github.sparkmuse.query.search.SearchTranslationsQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SearchQueryTest {

    @Nested
    inner class Translation {
        @Test
        fun `parameters gets all query parameters as a map`() {

            val searchTranslationQuery = Fixture.fixture().create(SearchTranslationsQuery::class.java)

            val expected = mapOf(
                "q" to searchTranslationQuery.q,
                "prefix" to searchTranslationQuery.prefix.toString(),
                "limit" to searchTranslationQuery.limit.toString(),
                "offset" to searchTranslationQuery.offset.toString(),
            )

            val actual = searchTranslationQuery.parameters()

            assertThat(actual).containsExactlyEntriesOf(expected)
        }
    }
}