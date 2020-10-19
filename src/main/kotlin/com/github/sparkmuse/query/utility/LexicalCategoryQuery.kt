package com.github.sparkmuse.query.utility

import com.github.sparkmuse.query.LanguageBilingual
import com.github.sparkmuse.query.LanguageMonolingual
import com.github.sparkmuse.query.Query

/**
 * Lists available LexicalCategory in a monolingual dataset
 */
class LexicalCategoryMonolingualQuery(

    /**
     * Language code of the source language in a monolingual dataset
     */
    val sourceLanguage: LanguageMonolingual = LanguageMonolingual.English_gb,

    ) : Query {

    override val queryParams: String get() = ""

    override val pathFragment: String
        get() = "lexicalCategories/${sourceLanguage.value}"
}

/**
 * Lists available LexicalCategory in a bilingual dataset
 */
class LexicalCategoryBilingualQuery(

    /**
     * Language code of the source language in a bilingual dataset.
     */
    val sourceLanguage: LanguageBilingual = LanguageBilingual.English,

    /**
     * Language code of the target language in a bilingual dataset.
     */
    val targetLanguage: LanguageBilingual = LanguageBilingual.Spanish,

    ) : Query {

    override val queryParams: String get() = ""

    override val pathFragment: String
        get() = "lexicalCategories/${sourceLanguage.value}/${targetLanguage.value}"
}