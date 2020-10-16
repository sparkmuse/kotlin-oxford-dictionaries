package com.github.sparkmuse.common

interface Query {
    fun parameters(): Map<String, String>
    fun pathFragment(): String
}