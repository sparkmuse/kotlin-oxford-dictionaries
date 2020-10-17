package com.github.sparkmuse

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sparkmuse.entity.RetrieveEntry
import com.github.sparkmuse.entity.Lemmatron
import com.github.sparkmuse.entity.Thesaurus
import com.github.sparkmuse.entity.WordList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("correctly deserializes")
class OxfordContractTest {

    @Test
    fun entries() {

        val json = OxfordContractTest::class.java.getResource("/__files/entries.json").readText()

        val retrieveEntry = jacksonObjectMapper().readValue(json, RetrieveEntry::class.java)

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    fun lemmas() {

        val json = OxfordContractTest::class.java.getResource("/__files/lemmas.json").readText()

        val lemmatron = jacksonObjectMapper().readValue(json, Lemmatron::class.java)

        assertThat(lemmatron).isNotNull
    }

    @Test
    fun `search translations`() {

        val json = OxfordContractTest::class.java.getResource("/__files/search_translations.json").readText()

        val wordlist = jacksonObjectMapper().readValue(json, WordList::class.java)

        assertThat(wordlist).isNotNull
    }

    @Test
    fun search() {

        val json = OxfordContractTest::class.java.getResource("/__files/search.json").readText()

        val wordlist = jacksonObjectMapper().readValue(json, WordList::class.java)

        assertThat(wordlist).isNotNull
    }

    @Test
    fun `search thesaurus`() {

        val json = OxfordContractTest::class.java.getResource("/__files/search_thesaurus.json").readText()

        val thesaurusWordList = jacksonObjectMapper().readValue(json, WordList::class.java)

        assertThat(thesaurusWordList).isNotNull
    }

    @Test
    fun thesaurus() {

        val json = OxfordContractTest::class.java.getResource("/__files/thesaurus.json").readText()

        val thesaurusWordList = jacksonObjectMapper().readValue(json, Thesaurus::class.java)

        assertThat(thesaurusWordList).isNotNull
    }
}