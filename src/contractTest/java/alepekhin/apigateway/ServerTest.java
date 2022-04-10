package alepekhin.apigateway;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.Test;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ServerTest extends UserBase {

    @Test
    public void validate_shouldReturnStatus200() throws Exception {
        // given:
        MockMvcRequestSpecification request = given();


        // when:
        ResponseOptions response = given().spec(request)
                .get("/users");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);

        // and:
        System.out.println(response.getBody().asString());
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).field("['name']").isEqualTo("Ervin Howell");
    }
}
