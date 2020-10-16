package com.github.sparkmuse

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.sparkmuse.entity.RetrieveEntry
import com.github.sparkmuse.entity.Lemmatron
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class OxfordContractTest {

    @Test
    @DisplayName("/entries")
    fun entries() {

        val json = OxfordContractTest::class.java.getResource("/__files/entries.json").readText()

        val retrieveEntry = jacksonObjectMapper().readValue(json, RetrieveEntry::class.java)

        assertThat(retrieveEntry).isNotNull
    }

    @Test
    @DisplayName("correctly deserializes lemmas")
    fun lemmas() {

        val json = OxfordContractTest::class.java.getResource("/__files/lemmas.json").readText()

        val lemmatron = jacksonObjectMapper().readValue(json, Lemmatron::class.java)

        assertThat(lemmatron).isNotNull
    }
}