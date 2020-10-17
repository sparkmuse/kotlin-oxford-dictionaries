package com.github.sparkmuse.internal

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParseKtTest {

    @Test
    fun parse() {
        val json = """{"name": "My Name"}"""
        val actual = parse<Name>(json)

        val expected = Name("My Name")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun exception() {
        val json = """{INVALID_JSON}"""
        val actual = parse<Name>(json)

        val expected = Name("My Name")
        assertThat(actual).isNull()
    }
}

data class Name(val name: String)