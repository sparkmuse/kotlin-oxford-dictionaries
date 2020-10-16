package com.github.sparkmuse.query

enum class LanguageMonolingual(val value: String) {
    English_gb("en-gb"),
    English_us("en-us"),
    Spanish("es"),
    French("fr"),
    Gujarati("gu"),
    Hindi("hi"),
    Latvian("lv"),
    Romanian("ro"),
    Swahili("sw"),
    Tamil("ta")
}

enum class LanguageBilingual() {
    en, ar, de, el, es, ha, hi, id, it, mr, ms, nso, pt, qu, ru, te, tk, tn, tpi, tt, ur, xh, zh, zu;
}

enum class LanguageThesaurus() {
    en
}