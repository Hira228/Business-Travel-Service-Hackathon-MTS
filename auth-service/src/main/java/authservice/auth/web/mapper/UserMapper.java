package authservice.auth.web.mapper;

import authservice.auth.entity.User;
import authservice.auth.web.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDTO> {
    @Override
    User toEntity(UserDTO dto);
}
