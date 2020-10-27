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
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = FilterQuery()
            val actual = query.fragments
            assertThat(actual).containsExactly("filters")
        }
    }

    @Nested
    inner class FilterEndpoint {

        @TestWithFixture
        fun `parameters gets always empty map`(query: FilterEndpointQuery) {
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = FilterEndpointQuery("entries")
            val actual = query.fragments
            assertThat(actual).containsExactly("filters", "entries")
        }
    }
}