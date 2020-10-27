package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageBilingual.English
import com.github.sparkmuse.query.LanguageBilingual.Spanish
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.utility.DomainBilingualQuery
import com.github.sparkmuse.query.utility.DomainMonolingualQuery
import com.github.sparkmuse.query.utility.FieldEndpointQuery
import com.github.sparkmuse.query.utility.FieldQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class FieldQueryTest {

    @Nested
    inner class Field {
        @TestWithFixture
        fun `parameters gets always empty map`(query: FieldQuery) {
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = FieldQuery()
            val actual = query.fragments
            assertThat(actual).containsExactly("fields")
        }
    }

    @Nested
    inner class FieldEndpoint {

        @TestWithFixture
        fun `parameters gets always empty map`(query: FieldEndpointQuery) {
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = FieldEndpointQuery("entities")
            val actual = query.fragments
            assertThat(actual).containsExactly("fields", "entities")
        }
    }
}