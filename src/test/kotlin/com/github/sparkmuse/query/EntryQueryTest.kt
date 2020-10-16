package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EntryQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: EntryQuery) {

        val expected = mapOf(
            "fields" to query.fields.joinToString(","),
            "grammaticalFeatures" to query.grammaticalFeatures.joinToString(","),
            "lexicalCategory" to query.lexicalCategory.joinToString(","),
            "domains" to query.domains.joinToString(","),
            "registers" to query.registers.joinToString(","),
            "strictMatch" to query.strictMatch.toString()
        )

        val actual = query.parameters()

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val entryQuery = EntryQuery("ace")

        val actual = entryQuery.parameters()

        val expected = mapOf(
            "strictMatch" to entryQuery.strictMatch.toString()
        )

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = EntryQuery("ace", LanguageMonolingual.English_gb)
        val actual = query.pathFragment()
        assertThat(actual).isEqualTo("entries/en-gb/ace")
    }
}