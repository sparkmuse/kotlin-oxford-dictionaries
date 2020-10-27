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
        )

        val actual = query.parameters

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = LemmaQuery("ace", LanguageMonolingual.English_gb)
        val actual = query.fragments
        assertThat(actual).containsExactly("lemmas", "en-gb", "ace")
    }
}