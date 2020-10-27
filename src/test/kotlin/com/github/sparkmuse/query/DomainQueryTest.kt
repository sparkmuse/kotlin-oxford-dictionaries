package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageBilingual.English
import com.github.sparkmuse.query.LanguageBilingual.Spanish
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.utility.DomainBilingualQuery
import com.github.sparkmuse.query.utility.DomainMonolingualQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DomainQueryTest {

    @Nested
    inner class Monolingual {
        @TestWithFixture
        fun `parameters gets always empty map`(query: DomainMonolingualQuery) {
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = DomainMonolingualQuery(English_gb)
            val actual = query.fragments
            assertThat(actual).containsExactly("domains", "en-gb")
        }
    }

    @Nested
    inner class Bilingual {

        @TestWithFixture
        fun `parameters gets always empty map`(query: DomainBilingualQuery) {
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = DomainBilingualQuery(English, Spanish)
            val actual = query.fragments
            assertThat(actual).containsExactly("domains", "en", "es")
        }
    }
}