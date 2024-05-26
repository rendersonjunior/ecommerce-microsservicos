package br.com.rendersonjunior.ecommerceuserapi.service.user;

import br.com.rendersonjunior.ecommerceuserapi.model.User;
import com.rendersonjunior.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAll();

    UserDTO findById(long userId);

    User save(User user);

    UserDTO delete(long userId);

    User findByCpf(final String cpf, final String key);

    List<UserDTO> queryByName(String name);

    User editUser(Long userId, User userUpdate);

    Page<UserDTO> getAllPage(Pageable page);

}
