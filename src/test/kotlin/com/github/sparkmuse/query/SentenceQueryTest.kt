package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageSentence.English
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SentenceQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: SentenceQuery) {

        val expected = mapOf(
            "strictMatch" to query.strictMatch.toString()
        )

        val actual = query.parameters()

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = SentenceQuery(word = "ace", sourceLanguage = English)
        val actual = query.pathFragment()
        assertThat(actual).isEqualTo("sentences/en/ace")
    }
}