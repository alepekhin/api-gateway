package alepekhin.apigateway.user;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WireMockTest {

    @Autowired
    private MockMvc mockMvc;

    protected static WireMockServer wireMockServer;

    @BeforeAll
    public static void init() {
        wireMockServer = new WireMockServer(
                new WireMockConfiguration().port(7070)
        );
        wireMockServer.start();
        WireMock.configureFor("localhost", 7070);
    }

    @AfterAll
    public static void after() {
        wireMockServer.shutdown();
    }

    @Test
    public void testStub() throws Exception {
        System.out.println("inside testStub");


        stubFor(WireMock.get(urlMatching("/users")) // called on wiremock server localhost:7070
                .willReturn(aResponse().withHeader("Content-TYpe", "application/json")
                        .withStatus(200).withBodyFile("users.json"))); // file inside resources/__files

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users") // called on controller
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("stub returned "+ result.getResponse().getContentAsString());

    }
}
