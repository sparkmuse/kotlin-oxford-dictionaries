package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordQueryTest {

    @TestWithFixture(minCollectionSize = 1, maxCollectionSize = 1)
    fun `parameters gets all query parameters as a map`(query: WordQuery) {

        val expected = mapOf(
            "q" to query.q,
            "fields" to query.fields.joinWithComma(),
            "grammaticalFeatures" to query.grammaticalFeatures.joinWithComma(),
            "lexicalCategory" to query.lexicalCategory.joinWithComma(),
            "domains" to query.domains.joinWithComma(),
            "registers" to query.registers.joinWithComma(),
        ).joinWithAmpersand()

        val actual = query.queryParams

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val wordQuery = WordQuery(q = "ace")

        val actual = wordQuery.queryParams

        val expected = mapOf(
            "q" to wordQuery.q
        ).joinWithAmpersand()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun pathFragment() {
        val query = WordQuery(q = "ace", sourceLanguage = English_gb)
        val actual = query.pathFragment
        assertThat(actual).isEqualTo("words/en-gb")
    }
}