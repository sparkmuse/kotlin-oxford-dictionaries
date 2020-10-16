package com.github.sparkmuse.lemmas

import com.github.nylle.javafixture.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LemmaQueryTest {

    @Test
    fun `parameters gets all query parameters as a map`() {

        val entryQuery = Fixture.fixture().create(LemmaQuery::class.java)

        val expected = mapOf(
            "grammaticalFeatures" to entryQuery.grammaticalFeatures.joinToString(","),
            "lexicalCategory" to entryQuery.lexicalCategory.joinToString(","),
        )

        val actual = entryQuery.parameters()

        assertThat(actual).containsExactlyEntriesOf(expected)
    }

    @Test
    fun `parameters gets NON empty query parameters`() {

        val entryQuery = LemmaQuery("ace", grammaticalFeatures = listOf("abbreviation"))

        val actual = entryQuery.parameters()

        val expected = mapOf("grammaticalFeatures" to "abbreviation")
        assertThat(actual).containsExactlyEntriesOf(expected)
    }
}