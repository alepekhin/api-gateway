package alepekhin.apigateway.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH,
        ids = "5334b05c-f5c8-48a5-adc3-922fdb7533fc")
public class BackendContractTest extends WireMockTestBase {

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
