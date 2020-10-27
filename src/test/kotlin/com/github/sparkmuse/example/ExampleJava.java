package com.github.sparkmuse.example;

import com.github.sparkmuse.OxfordClient;
import com.github.sparkmuse.entity.GrammaticalFeature;
import com.github.sparkmuse.entity.RetrieveEntry;
import com.github.sparkmuse.entity.RetrieveGrammaticalFeature;
import com.github.sparkmuse.query.DataField;
import com.github.sparkmuse.query.EntryQuery;
import com.github.sparkmuse.query.LanguageMonolingual;
import com.github.sparkmuse.query.utility.GrammaticalFeatureMonolingualQuery;
import com.github.sparkmuse.wiremock.Wiremock;
import com.github.sparkmuse.wiremock.WiremockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.http.HttpClient;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(WiremockExtension.class)
class ExampleJava {

    @Wiremock
    private WireMockServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer.stubFor(
                get(anyUrl())
                        .willReturn(
                                aResponse()
                                        .withStatus(200)
                                        .withBodyFile("entries.json")
                        )
        );
    }

    @Test
    @DisplayName("gets entries for the word 'ace'")
    void entries() {

        String appId = System.getenv("APP_ID");
        String appKey = System.getenv("APP_KEY");
        String baseUrl = "https://od-api.oxforddictionaries.com/api/v2";

        OxfordClient oxfordClient = new OxfordClient(appId, appKey, baseUrl);

        RetrieveEntry entries = oxfordClient.entries("ace");

        assertNotNull(entries);
    }

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
}
