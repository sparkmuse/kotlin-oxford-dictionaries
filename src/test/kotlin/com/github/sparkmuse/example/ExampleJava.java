package com.github.sparkmuse.example;

import com.github.sparkmuse.OxfordClient;
import com.github.sparkmuse.entity.RetrieveEntry;
import com.github.sparkmuse.wiremock.Wiremock;
import com.github.sparkmuse.wiremock.WiremockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(WiremockExtension.class)
class ExampleJava {

    @Wiremock
    private WireMockServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer.stubFor(
                get(urlEqualTo("https://od-api.oxforddictionaries.com/api/v2/entries/en-gb/ace"))
                        .withQueryParam("strictMatch", equalTo("false"))
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
}
