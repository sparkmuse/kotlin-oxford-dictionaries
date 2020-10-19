package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageBilingual.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TranslationQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: TranslationQuery) {

        val expected = mapOf(
            "fields" to query.fields.joinWithComma(),
            "grammaticalFeatures" to query.grammaticalFeatures.joinWithComma(),
            "lexicalCategory" to query.lexicalCategory.joinWithComma(),
            "domains" to query.domains.joinWithComma(),
            "registers" to query.registers.joinWithComma(),
            "strictMatch" to query.strictMatch.toString()
        ).joinWithAmpersand()

        val actual = query.queryParams

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val entryQuery = TranslationQuery(word = "ace")

        val actual = entryQuery.queryParams

        val expected = mapOf(
            "strictMatch" to entryQuery.strictMatch.toString()
        ).joinWithAmpersand()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun pathFragment() {
        val query = TranslationQuery(word = "ace", sourceLanguage = English, targetLanguage = Spanish)
        val actual = query.pathFragment
        assertThat(actual).isEqualTo("translations/en/es/ace")
    }
}