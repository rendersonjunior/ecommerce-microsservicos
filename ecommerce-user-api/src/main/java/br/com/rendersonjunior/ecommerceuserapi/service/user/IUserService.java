package br.com.rendersonjunior.ecommerceuserapi.service.user;

import com.rendersonjunior.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAll();

    UserDTO findById(long userId);

    UserDTO save(UserDTO userDTO);

    UserDTO delete(long userId);

    UserDTO findByCpf(String cpf);

    List<UserDTO> queryByName(String name);

    UserDTO editUser(Long userId, UserDTO userDTO);

    Page<UserDTO> getAllPage(Pageable page);

}
