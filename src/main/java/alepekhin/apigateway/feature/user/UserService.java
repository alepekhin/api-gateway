package alepekhin.apigateway.feature.user;

import alepekhin.apigateway.common.RestHttpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestHttpClient httpClient;

    @Value("${backend-url}")
    private String backendUrl;

    public List<User> getUsers() {
        ParameterizedTypeReference<List<User>> typeReference =
                new ParameterizedTypeReference<List<User>>() {
                };

        return httpClient.getObject(backendUrl+"/users", null, typeReference);
    }

}
