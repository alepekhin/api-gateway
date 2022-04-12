package alepekhin.apigateway.feature.user;

import alepekhin.apigateway.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    @Mappings({
            @Mapping(target="fullName", source="user.name")
            ,@Mapping(target="city", source="user.address.city")
    })
    UserDTO userToUserDTO(User user);

    List<UserDTO> listUserToListUserDTO(List<User> listUser);

}
