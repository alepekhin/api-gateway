package alepekhin.apigateway.user;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

    @BeforeEach
    void init() {
        WireMockServer wireMockServer = new WireMockServer(
                new WireMockConfiguration().port(7070)
        );
        wireMockServer.start();
        WireMock.configureFor("localhost", 7070);
    }

    @Test
    public void testStub() throws Exception {
        System.out.println("inside testStub");


        stubFor(WireMock.get(urlMatching("/users"))
                .willReturn(aResponse().withHeader("Content-TYpe", "application/json")
                        .withStatus(200).withBodyFile("users.json")));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("stub returned "+ result.getResponse().getContentAsString());

    }
}
