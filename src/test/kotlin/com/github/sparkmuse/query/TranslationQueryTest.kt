package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageBilingual.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TranslationQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: TranslationQuery) {

        val expected = mapOf(
            "fields" to query.fields.joinToString(","),
            "grammaticalFeatures" to query.grammaticalFeatures.joinToString(","),
            "lexicalCategory" to query.lexicalCategory.joinToString(","),
            "domains" to query.domains.joinToString(","),
            "registers" to query.registers.joinToString(","),
            "strictMatch" to query.strictMatch.toString()
        )

        val actual = query.parameters()

        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val entryQuery = TranslationQuery(word = "ace")

        val actual = entryQuery.parameters()

        val expected = mapOf(
            "strictMatch" to entryQuery.strictMatch.toString()
        )

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = TranslationQuery(word = "ace", sourceLanguage = English, targetLanguage = Spanish)
        val actual = query.pathFragment()
        assertThat(actual).isEqualTo("translations/en/es/ace")
    }
}