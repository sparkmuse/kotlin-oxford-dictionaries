# kotlin-oxford-dictionaries
Kotlin client for the Oxford Dictionaries API

## Supported endpoints

| Api                                                                              	| Supported? 	|
|----------------------------------------------------------------------------------	|:----------:	|
|                                                                                  	|            	|
| /api/v2/entries/{source_lang}/{word_id}:                                         	|      ✅     	|
| /api/v2/lemmas/{source_lang}/{word_id}:                                          	|      ✅     	|
| /api/v2/translations/{source_lang_translate}/{target_lang_translate}/{word_id}:  	|      ✅     	|
| /api/v2/thesaurus/{lang}/{word_id}:                                              	|      ✅     	|
| /api/v2/sentences/{source_lang}/{word_id}:                                       	|      ✅     	|
| /api/v2/words/{source_lang}:                                                     	|      ❌     	|
| /inflections/{source_lang}/{word_id}:                                            	|      ❌     	|
|                                                                                   |               |
| __Search__                                                                      	|            	|
| /api/v2/search/translations/{source_lang_search}/{target_lang_search}:           	|      ✅     	|
| /api/v2/search/{source_lang}:                                                    	|      ✅     	|
| /api/v2/search/thesaurus/{source_lang}                                           	|      ✅     	|
|                                                                                   |               |
| __Utility__                                                                      	|            	|
| /api/v2/domains/{source_lang}:                                                   	|      ❌     	|
| /api/v2/domains/{source_lang_domains}/{target_lang_domains}:                     	|      ❌     	|
| /api/v2/fields:                                                                  	|      ❌     	|
| /api/v2/fields/{endpoint}:                                                       	|      ❌     	|
| /api/v2/filters:                                                                 	|      ❌     	|
| /api/v2/filters/{endpoint}:                                                      	|      ❌     	|
| /api/v2/grammaticalFeatures/{source_lang}:                                       	|      ❌     	|
| /api/v2/grammaticalFeatures/{source_lang_grammatical}/{target_lang_grammatical}: 	|      ❌     	|
| /api/v2/languages:                                                               	|      ❌     	|
| /api/v2/lexicalCategories/{source_lang}:                                         	|      ❌     	|
| /api/v2/lexicalCategories/{source_lang_lexical}/{target_lang_lexical}:           	|      ❌     	|
| /api/v2/registers/{source_lang}:                                                 	|      ❌     	|
| /api/v2/registers/{source_lang_registers}/{target_lang_registers}:               	|      ❌     	|