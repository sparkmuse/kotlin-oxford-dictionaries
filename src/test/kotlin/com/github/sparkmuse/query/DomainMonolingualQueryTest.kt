package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DomainMonolingualQueryTest {

    @TestWithFixture
    fun `parameters gets all query parameters as a map`(query: DomainMonolingualQuery) {

        val actual = query.parameters()

        assertThat(actual).isEmpty()
    }

    @Test
    fun pathFragment() {
        val query = DomainMonolingualQuery(LanguageMonolingual.English_gb)
        val actual = query.pathFragment()
        assertThat(actual).isEqualTo("domains/en-gb")
    }
}