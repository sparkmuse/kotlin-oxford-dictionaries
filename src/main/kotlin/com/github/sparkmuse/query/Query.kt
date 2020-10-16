package com.github.sparkmuse.query

interface Query {
    fun parameters(): Map<String, String>
    fun pathFragment(): String
}