package com.github.sparkmuse.query

import com.github.nylle.javafixture.Fixture
import com.github.sparkmuse.query.search.SearchQuery
import com.github.sparkmuse.query.search.SearchThesaurusQuery
import com.github.sparkmuse.query.search.SearchTranslationsQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("parameters gets all query parameters as a map for")
class SearchQueryTest {

    @Test
    fun `search translations`() {

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

    @Test
    fun search() {

        val searchQuery = Fixture.fixture().create(SearchQuery::class.java)

        val expected = mapOf(
            "q" to searchQuery.q,
            "prefix" to searchQuery.prefix.toString(),
            "limit" to searchQuery.limit.toString(),
            "offset" to searchQuery.offset.toString(),
        )

        val actual = searchQuery.parameters()

        assertThat(actual).containsExactlyEntriesOf(expected)

    }

    @Test
    fun `search thesaurus`() {

        val searchThesaurusQuery = Fixture.fixture().create(SearchThesaurusQuery::class.java)

        val expected = mapOf(
            "q" to searchThesaurusQuery.q,
            "prefix" to searchThesaurusQuery.prefix.toString(),
            "limit" to searchThesaurusQuery.limit.toString(),
            "offset" to searchThesaurusQuery.offset.toString(),
        )

        val actual = searchThesaurusQuery.parameters()

        assertThat(actual).containsExactlyEntriesOf(expected)

    }
}