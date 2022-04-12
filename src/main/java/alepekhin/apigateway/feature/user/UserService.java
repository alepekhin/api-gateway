package alepekhin.apigateway.feature.user;

import alepekhin.apigateway.common.RestHttpClient;
import alepekhin.apigateway.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RestHttpClient httpClient;
    private final UserDTOMapper userDTOMapper;

    @Value("${backend-url}")
    private String backendUrl;

    public List<UserDTO> getUsers() {
        ParameterizedTypeReference<List<User>> typeReference =
                new ParameterizedTypeReference<List<User>>() {
                };

        var users =  httpClient.getObject(backendUrl+"/users", null, typeReference);
        return userDTOMapper.listUserToListUserDTO(users);
    }

}
