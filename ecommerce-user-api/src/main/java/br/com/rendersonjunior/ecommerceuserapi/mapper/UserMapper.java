package br.com.rendersonjunior.ecommerceuserapi.mapper;

import br.com.rendersonjunior.ecommerceuserapi.model.User;
import com.rendersonjunior.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromDTO(UserDTO userDTO);

    UserDTO toDTO(User user);

}
