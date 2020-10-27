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
        )

        val actual = query.parameters

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = TranslationQuery(word = "ace", sourceLanguage = English, targetLanguage = Spanish)
        val actual = query.fragments
        assertThat(actual).containsExactly("translations", "en", "es", "ace")
    }
}