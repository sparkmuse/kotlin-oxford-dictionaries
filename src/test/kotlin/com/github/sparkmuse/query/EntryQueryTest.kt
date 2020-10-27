package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EntryQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: EntryQuery) {

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
        val query = EntryQuery("ace", LanguageMonolingual.English_gb)
        val actual = query.fragments
        assertThat(actual).containsExactly("entries", "en-gb", "ace")
    }
}