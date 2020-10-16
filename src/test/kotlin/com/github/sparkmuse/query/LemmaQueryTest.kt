package com.github.sparkmuse.query

import com.github.nylle.javafixture.Fixture
import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LemmaQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: LemmaQuery) {

        val expected = mapOf(
            "grammaticalFeatures" to query.grammaticalFeatures.joinToString(","),
            "lexicalCategory" to query.lexicalCategory.joinToString(","),
        )

        val actual = query.parameters()

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val entryQuery = LemmaQuery("ace", grammaticalFeatures = listOf("abbreviation"))

        val actual = entryQuery.parameters()

        val expected = mapOf("grammaticalFeatures" to "abbreviation")
        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = LemmaQuery("ace", LanguageMonolingual.English_gb)
        val actual = query.pathFragment()
        assertThat(actual).isEqualTo("lemmas/en-gb/ace")
    }
}