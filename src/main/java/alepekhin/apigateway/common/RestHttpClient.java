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
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(token), typeReference);
        return response.getBody();
    }

    private HttpEntity<Object> createHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        headers.set(Constants.X_REQUEST_ID, MDC.get(Constants.MDC_RQID));
        return new HttpEntity<>(headers);
    }

}