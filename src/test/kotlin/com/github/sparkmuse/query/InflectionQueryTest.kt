package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.LanguageSentence.English
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InflectionQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: InflectionQuery) {

        val expected = mapOf(
            "strictMatch" to query.strictMatch.toString()
        )

        val actual = query.parameters()

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = InflectionQuery(word = "ace", sourceLanguage = English_gb)
        val actual = query.pathFragment()
        assertThat(actual).isEqualTo("inflections/en-gb/ace")
    }
}