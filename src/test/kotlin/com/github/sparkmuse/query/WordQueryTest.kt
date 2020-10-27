package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WordQueryTest {

    @TestWithFixture(minCollectionSize = 1, maxCollectionSize = 1)
    fun `parameters gets all query parameters as a map`(query: WordQuery) {

        val expected = mapOf(
            "q" to query.q,
            "fields" to query.fields.joinWithComma(),
            "grammaticalFeatures" to query.grammaticalFeatures.joinWithComma(),
            "lexicalCategory" to query.lexicalCategory.joinWithComma(),
            "domains" to query.domains.joinWithComma(),
            "registers" to query.registers.joinWithComma(),
        )

        val actual = query.parameters

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun pathFragment() {
        val query = WordQuery(q = "ace", sourceLanguage = English_gb)
        val actual = query.fragments
        assertThat(actual).containsExactly("words", "en-gb")
    }
}