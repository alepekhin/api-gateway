package alepekhin.apigateway.user;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH,
        ids = "5334b05c-f5c8-48a5-adc3-922fdb7533fc")
@ActiveProfiles("test")
public class BackendContractTest {

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
    public void testBackendContract() throws Exception {
        System.out.println("inside testBackendContract");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users") // called on controller
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("stub returned "+ result.getResponse().getContentAsString());
    }
}
