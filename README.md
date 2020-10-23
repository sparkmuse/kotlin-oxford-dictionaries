# Kotlin Oxford Dictionaries
![Build](https://github.com/sparkmuse/kotlin-oxford-dictionaries/workflows/Build/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sparkmuse_kotlin-oxford-dictionaries&metric=alert_status)](https://sonarcloud.io/dashboard?id=sparkmuse_kotlin-oxford-dictionaries)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sparkmuse_kotlin-oxford-dictionaries&metric=coverage)](https://sonarcloud.io/dashboard?id=sparkmuse_kotlin-oxford-dictionaries)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.sparkmuse/kotlin-oxford-dictionaries.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.sparkmuse/kotlin-oxford-dictionaries)
![GitHub](https://img.shields.io/github/license/sparkmuse/kotlin-oxford-dictionaries)

Kotlin client for the Oxford Dictionaries API. 

This projects aims to facilitate the interaction with the Oxford Dictionaries API. A complete documentation for the 
API can be reached at: [https://developer.oxforddictionaries.com/documentation](https://developer.oxforddictionaries.com/documentation).

## Contents
- [Kotlin Oxford Dictionaries](#kotlin-oxford-dictionaries)
  * [Supported endpoints](#supported-endpoints)
  * [Install](#install)
  * [Authentication App Key and Id](#authentication-app-key-and-id)
  * [Usage & Examples](#usage---examples)
    + [Retrieve entries for the word 'ace'](#retrieve-entries-for-the-word--ace-)
    + [Retrieve entries for the word 'ace' with complex query](#retrieve-entries-for-the-word--ace--with-complex-query)
    + [Retrieve grammatical features for 'en-us' language](#retrieve-grammatical-features-for--en-us--language)

## Supported endpoints

The API currently supports all endpoints.
<details>
<summary>endpoints</summary>
<p>

| Api                                                                              	| Supported? 	|
|----------------------------------------------------------------------------------	|:----------:	|
| /api/v2/entries/{source_lang}/{word_id}:                                         	|      ✅     	|
| /api/v2/lemmas/{source_lang}/{word_id}:                                          	|      ✅     	|
| /api/v2/translations/{source_lang_translate}/{target_lang_translate}/{word_id}:  	|      ✅     	|
| /api/v2/thesaurus/{lang}/{word_id}:                                              	|      ✅     	|
| /api/v2/sentences/{source_lang}/{word_id}:                                       	|      ✅     	|
| /api/v2/words/{source_lang}:                                                     	|      ✅     	|
| /api/v2/inflections/{source_lang}/{word_id}:                                    	|      ✅     	|
| __Search__                                                                      	|            	|
| /api/v2/search/translations/{source_lang_search}/{target_lang_search}:           	|      ✅     	|
| /api/v2/search/{source_lang}:                                                    	|      ✅     	|
| /api/v2/search/thesaurus/{source_lang}                                           	|      ✅     	|
| __Utility__                                                                      	|            	|
| /api/v2/domains/{source_lang}:                                                   	|      ✅     	|
| /api/v2/domains/{source_lang_domains}/{target_lang_domains}:                     	|      ✅     	|
| /api/v2/fields:                                                                  	|      ✅     	|
| /api/v2/fields/{endpoint}:                                                       	|      ✅     	|
| /api/v2/filters:                                                                 	|      ✅     	|
| /api/v2/filters/{endpoint}:                                                      	|      ✅     	|
| /api/v2/grammaticalFeatures/{source_lang}:                                       	|      ✅     	|
| /api/v2/grammaticalFeatures/{source_lang_grammatical}/{target_lang_grammatical}: 	|      ✅     	|
| /api/v2/languages:                                                               	|      ✅     	|
| /api/v2/lexicalCategories/{source_lang}:                                         	|      ✅     	|
| /api/v2/lexicalCategories/{source_lang_lexical}/{target_lang_lexical}:           	|      ✅     	|
| /api/v2/registers/{source_lang}:                                                 	|      ✅     	|
| /api/v2/registers/{source_lang_registers}/{target_lang_registers}:               	|      ✅     	|

</p>
</details>

## Install

All needed to start using the project is to add the dependency

**Maven** 
```xml
<dependency>
  <groupId>com.github.sparkmuse</groupId>
  <artifactId>kotlin-oxford-dictionaries</artifactId>
  <version>1.0.7</version>
</dependency>
```

**Gradle Kotlin DSL**
```shell script
implementation("com.github.sparkmuse:kotlin-oxford-dictionaries:1.0.7")
```

**Gradle**
```shell script
implementation 'com.github.sparkmuse:kotlin-oxford-dictionaries:1.0.7'
```

## Authentication App Key and Id

Oxford Dictionaries comes with three price tiers: Prototype, Developer and Research. A key can be obtained by following
the link [https://developer.oxforddictionaries.com/?tag=#plans](https://developer.oxforddictionaries.com/?tag=#plans).

Use the **AppId** and **AppKey**  when creating the client.

## Usage & Examples

Use the OxfordClient class to interact with the API.

### Retrieve entries for the word 'ace'

<details open>
<summary>kotlin</summary>
<p>

```kotlin
@Test
fun `retrieve entries for the word 'ace'`() {

    val appId = System.getenv("APP_ID")
    val appKey = System.getenv("APP_KEY")
    val baseUrl = "https://od-api.oxforddictionaries.com/api/v2"

    val oxfordClient = OxfordClient(appId, appKey, baseUrl)

    val entries = oxfordClient.entries("ace")

    assertNotNull(entries)
}
```

</p>
</details>

<details>
<summary>java</summary>
<p>

```java
@Test
@DisplayName("retrieve entries for the word 'ace'")
void entries() {

    String appId = System.getenv("APP_ID");
    String appKey = System.getenv("APP_KEY");
    String baseUrl = "https://od-api.oxforddictionaries.com/api/v2";

    OxfordClient oxfordClient = new OxfordClient(appId, appKey, baseUrl);

    RetrieveEntry entries = oxfordClient.entries("ace");

    assertNotNull(entries);
}
```

</p>
</details>

### Retrieve entries for the word 'ace' with complex query

<details open>
<summary>kotlin</summary>
<p>

```kotlin
@Test
fun `gets entries for the word 'ace' with complex query`() {

    val appId = System.getenv("APP_ID")
    val appKey = System.getenv("APP_KEY")
    val baseUrl = "https://od-api.oxforddictionaries.com/api/v2"

    val oxfordClient = OxfordClient(appId, appKey, baseUrl)

    val query = EntryQuery(
        word = "ace",
        sourceLanguage = LanguageMonolingual.English_us,
        fields = listOf(DataField.definitions),
        lexicalCategory = listOf("noun"),
        strictMatch = true
    )

    val entries = oxfordClient.entries(query)
    assertNotNull(entries)
}
```

</p>
</details>

<details>
<summary>java</summary>
<p>

```java
@Test
@DisplayName("gets entries for the word 'ace' with complex query")
void complexQueryEntries() {

    String appId = System.getenv("APP_ID");
    String appKey = System.getenv("APP_KEY");
    String baseUrl = "https://od-api.oxforddictionaries.com/api/v2";

    OxfordClient oxfordClient = new OxfordClient(appId, appKey, baseUrl);

    EntryQuery query = new EntryQuery(
            "ace",
            LanguageMonolingual.English_us,
            List.of(DataField.definitions),
            List.of(),
            List.of("noun"),
            List.of(),
            List.of(),
            true);
    RetrieveEntry entries = oxfordClient.entries(query);

    assertNotNull(entries);
}
```

</p>
</details>

### Retrieve grammatical features for 'en-us' language 

<details open>
<summary>kotlin</summary>
<p>

```kotlin
@Test
fun `gets grammatical features for 'en-us' language`() {

    val appId = System.getenv("APP_ID")
    val appKey = System.getenv("APP_KEY")
    val baseUrl = "https://od-api.oxforddictionaries.com/api/v2"

    val oxfordClient = OxfordClient(appId, appKey, baseUrl)

    val query = GrammaticalFeatureMonolingualQuery(
        sourceLanguage = LanguageMonolingual.English_us
    )

    val grammaticalFeature = oxfordClient.grammaticalFeatures(query)
    assertNotNull(grammaticalFeature)
}
```

</p>
</details>

<details>
<summary>java</summary>
<p>

```java
@Test
@DisplayName("gets grammatical features for 'en-us' language")
void grammaticalFeatures() {

    String appId = System.getenv("APP_ID");
    String appKey = System.getenv("APP_KEY");
    String baseUrl = "https://od-api.oxforddictionaries.com/api/v2";

    OxfordClient oxfordClient = new OxfordClient(appId, appKey, baseUrl);

    GrammaticalFeatureMonolingualQuery query =
            new GrammaticalFeatureMonolingualQuery(LanguageMonolingual.English_us);

    RetrieveGrammaticalFeature grammaticalFeature = oxfordClient.grammaticalFeatures(query);
    assertNotNull(grammaticalFeature);
}
```

</p>
</details>
