package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageBilingual.*
import com.github.sparkmuse.query.utility.LanguageQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LanguageQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: LanguageQuery) {

        val expected = mapOf(
            "sourceLanguage" to query.sourceLanguage?.value,
            "targetLanguage" to query.targetLanguage?.value
        )

        val actual = query.parameters

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = LanguageQuery()
        val actual = query.fragments
        assertThat(actual).containsExactly("languages")
    }
}