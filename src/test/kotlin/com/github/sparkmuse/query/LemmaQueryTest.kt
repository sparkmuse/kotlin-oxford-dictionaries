package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LemmaQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: LemmaQuery) {

        val expected = mapOf(
            "grammaticalFeatures" to query.grammaticalFeatures.joinWithComma(),
            "lexicalCategory" to query.lexicalCategory.joinWithComma(),
        ).joinWithAmpersand()

        val actual = query.queryParams

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val entryQuery = LemmaQuery("ace", grammaticalFeatures = listOf("abbreviation"))

        val actual = entryQuery.queryParams

        val expected = mapOf("grammaticalFeatures" to "abbreviation").joinWithAmpersand()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun pathFragment() {
        val query = LemmaQuery("ace", LanguageMonolingual.English_gb)
        val actual = query.pathFragment
        assertThat(actual).isEqualTo("lemmas/en-gb/ace")
    }
}