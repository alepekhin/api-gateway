package alepekhin.apigateway.common;

import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestHttpClient {

    private final RestTemplate restTemplate;

    public <T> T getObject(String url, String token, ParameterizedTypeReference<T> typeReference) {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(token, null), typeReference);
        System.out.println(response.getBody());
        return response.getBody();
    }
    private HttpEntity createHttpEntity(String token, Object object) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        headers.set(Constants.X_REQUEST_ID, MDC.get(Constants.MDC_RQID));
        if (object == null) {
            return new HttpEntity(headers);
        } else {
            return new HttpEntity(object, headers);
        }
    }

}