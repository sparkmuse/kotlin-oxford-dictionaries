package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageBilingual.English
import com.github.sparkmuse.query.LanguageBilingual.Spanish
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.utility.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FilterQueryTest {

    @Nested
    inner class Filter {
        @TestWithFixture
        fun `parameters gets always empty map`(query: FilterQuery) {
            assertThat(query.queryParams).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = FilterQuery()
            val actual = query.pathFragment
            assertThat(actual).isEqualTo("filters")
        }
    }

    @Nested
    inner class FilterEndpoint {

        @TestWithFixture
        fun `parameters gets always empty map`(query: FilterEndpointQuery) {
            assertThat(query.queryParams).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = FilterEndpointQuery("entries")
            val actual = query.pathFragment
            assertThat(actual).isEqualTo("filters/entries")
        }
    }
}