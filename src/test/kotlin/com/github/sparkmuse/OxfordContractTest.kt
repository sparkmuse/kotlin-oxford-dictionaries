package com.github.sparkmuse

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sparkmuse.entity.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("correctly deserializes")
class OxfordContractTest {

    private val clazz = OxfordContractTest::class.java

    @Test
    fun entries() {

        val json = clazz.getResource("/__files/entries.json").readText()

        val retrieveEntry = jacksonObjectMapper().readValue(json, RetrieveEntry::class.java)

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun lemmas() {

        val json = clazz.getResource("/__files/lemmas.json").readText()

        val lemmatron = jacksonObjectMapper().readValue(json, Lemmatron::class.java)

        assertThat(lemmatron).isNotNull
    }

    @Test
    fun `search translations`() {

        val json = clazz.getResource("/__files/search_translations.json").readText()

        val wordlist = jacksonObjectMapper().readValue(json, WordList::class.java)

        assertThat(wordlist).isNotNull
    }

    @Test
    fun search() {

        val json = clazz.getResource("/__files/search.json").readText()

        val wordlist = jacksonObjectMapper().readValue(json, WordList::class.java)

        assertThat(wordlist).isNotNull
    }

    @Test
    fun `search thesaurus`() {

        val json = clazz.getResource("/__files/search_thesaurus.json").readText()

        val thesaurusWordList = jacksonObjectMapper().readValue(json, WordList::class.java)

        assertThat(thesaurusWordList).isNotNull
    }

    @Test
    fun thesaurus() {

        val json = clazz.getResource("/__files/thesaurus.json").readText()

        val thesaurusWordList = jacksonObjectMapper().readValue(json, Thesaurus::class.java)

        assertThat(thesaurusWordList).isNotNull
    }

    @Test
    fun translations() {

        val json = clazz.getResource("/__files/translations.json").readText()

        val translation = jacksonObjectMapper().readValue(json, RetrieveTranslation::class.java)

        assertThat(translation).isNotNull
    }
}