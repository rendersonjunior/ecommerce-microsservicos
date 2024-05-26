package br.com.rendersonjunior.ecommerceuserapi.service.user;

import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapper;
import br.com.rendersonjunior.ecommerceuserapi.model.User;
import br.com.rendersonjunior.ecommerceuserapi.repository.UserRepository;
import com.rendersonjunior.dto.UserDTO;
import com.rendersonjunior.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAll() {
        final var usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId) {
        return userMapper.toDTO(userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new));
    }

    public User save(User user) {
        user.setKey(UUID.randomUUID().toString());
        user.setDataCadastro(LocalDateTime.now());
        return userRepository.save(user);
    }

    public UserDTO delete(long userId) {
        final var user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return userMapper.toDTO(user);
    }

    public User findByCpf(final String cpf, final String key) {
        final var user = userRepository.findByCpfAndKey(cpf, key);
        if (nonNull(user)) {
            return user;
        }
        throw new UserNotFoundException();
    }

    public List<UserDTO> queryByName(String name) {
        final var usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public User editUser(Long userId, User userUpdate) {
        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not exists"));

        if (nonNull(userUpdate.getNome()) && !user.getNome().equals(userUpdate.getNome())) {
            user.setNome(userUpdate.getNome());
        }
        if (userUpdate.getEmail() != null && !user.getEmail().equals(userUpdate.getEmail())) {
            user.setEmail(userUpdate.getEmail());
        }

        if (userUpdate.getTelefone() != null && !user.getTelefone().equals(userUpdate.getEmail())) {
            user.setTelefone(userUpdate.getTelefone());
        }

        if (userUpdate.getEndereco() != null && !user.getEndereco().equals(userUpdate.getEndereco())) {
            user.setEndereco(userUpdate.getEndereco());
        }

        return userRepository.save(user);
    }

    public Page<UserDTO> getAllPage(Pageable page) {
        return userRepository.findAll(page).map(userMapper::toDTO);
    }

}