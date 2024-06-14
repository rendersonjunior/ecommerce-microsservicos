package br.com.rendersonjunior.ecommerceuserapi.service.user;

import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapper;
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
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId) {
        return userMapper.toDTO(userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new));
    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());
        userDTO.setDataCadastro(LocalDateTime.now());
        return userMapper.toDTO(userRepository.save(userMapper.fromDTO(userDTO)));
    }

    public UserDTO delete(long userId) {
        final var user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return userMapper.toDTO(user);
    }

    public UserDTO findByCpf(final String cpf, final String key) {
        final var user = userRepository.findByCpfAndKey(cpf, key);
        if (nonNull(user)) {
            return userMapper.toDTO(user);
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

    public UserDTO editUser(Long userId, UserDTO userUpdateDTO) {
        final var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not exists"));

        if (nonNull(userUpdateDTO.getNome()) && !user.getNome().equals(userUpdateDTO.getNome())) {
            user.setNome(userUpdateDTO.getNome());
        }
        if (userUpdateDTO.getEmail() != null && !user.getEmail().equals(userUpdateDTO.getEmail())) {
            user.setEmail(userUpdateDTO.getEmail());
        }

        if (userUpdateDTO.getTelefone() != null && !user.getTelefone().equals(userUpdateDTO.getEmail())) {
            user.setTelefone(userUpdateDTO.getTelefone());
        }

        if (userUpdateDTO.getEndereco() != null && !user.getEndereco().equals(userUpdateDTO.getEndereco())) {
            user.setEndereco(userUpdateDTO.getEndereco());
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    public Page<UserDTO> getAllPage(Pageable page) {
        return userRepository.findAll(page).map(userMapper::toDTO);
    }

}
