package com.github.sparkmuse.query

import com.github.nylle.javafixture.annotations.fixture.TestWithFixture
import com.github.sparkmuse.query.LanguageBilingual.English
import com.github.sparkmuse.query.LanguageBilingual.Spanish
import com.github.sparkmuse.query.LanguageMonolingual.English_gb
import com.github.sparkmuse.query.utility.DomainBilingualQuery
import com.github.sparkmuse.query.utility.DomainMonolingualQuery
import com.github.sparkmuse.query.utility.GrammaticalFeatureBilingualQuery
import com.github.sparkmuse.query.utility.GrammaticalFeatureMonolingualQuery
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GrammaticalFeatureQueryTest {

    @Nested
    inner class Monolingual {
        @Test
        fun `parameters gets always empty map`() {
            val query = GrammaticalFeatureMonolingualQuery()
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = GrammaticalFeatureMonolingualQuery(English_gb)
            val actual = query.fragments
            assertThat(actual).containsExactly("grammaticalFeatures", "en-gb")
        }
    }

    @Nested
    inner class Bilingual {

        @TestWithFixture
        fun `parameters gets always empty map`(query: GrammaticalFeatureBilingualQuery) {
            assertThat(query.parameters).isEmpty()
        }

        @Test
        fun pathFragment() {
            val query = GrammaticalFeatureBilingualQuery(English, Spanish)
            val actual = query.fragments
            assertThat(actual).containsExactly("grammaticalFeatures", "en", "es")
        }
    }
}