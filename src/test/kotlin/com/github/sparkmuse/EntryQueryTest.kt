package com.github.sparkmuse

import com.github.nylle.javafixture.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Test
import javax.management.Query

class EntryQueryTest {

    @Test
    fun `parameters gets all query parameters as a map`() {

        val entryQuery = Fixture.fixture().create(EntryQuery::class.java)

        val expected = mapOf(
            "fields" to entryQuery.fields.joinToString(","),
            "grammaticalFeatures" to entryQuery.grammaticalFeatures.joinToString(","),
            "lexicalCategory" to entryQuery.lexicalCategory.joinToString(","),
            "domains" to entryQuery.domains.joinToString(","),
            "registers" to entryQuery.registers.joinToString(","),
            "strictMatch" to entryQuery.strictMatch.toString()
        )

        val actual = entryQuery.parameters()

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val entryQuery = EntryQuery("ace")

        val actual = entryQuery.parameters()

        val expected = mapOf(
            "strictMatch" to entryQuery.strictMatch.toString()
        )

        assertThat(actual).containsExactlyEntriesOf(expected)
    }
}