package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: WordQuery) {

        val expected = mapOf(
            "q" to query.q,
            "fields" to query.fields.joinToString(","),
            "grammaticalFeatures" to query.grammaticalFeatures.joinToString(","),
            "lexicalCategory" to query.lexicalCategory.joinToString(","),
            "domains" to query.domains.joinToString(","),
            "registers" to query.registers.joinToString(","),
        )

        val actual = query.parameters()

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val wordQuery = WordQuery(q = "ace")

        val actual = wordQuery.parameters()

        val expected = mapOf(
            "q" to wordQuery.q
        )

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = WordQuery(q = "ace", sourceLanguage = English_gb)
        val actual = query.pathFragment()
        assertThat(actual).isEqualTo("words/en-gb")
    }
}