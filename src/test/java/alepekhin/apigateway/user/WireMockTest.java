package alepekhin.apigateway.user;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class WireMockTest extends WireMockTestBase {

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
