package br.com.rendersonjunior.ecommerceuserapi.service;

import br.com.rendersonjunior.ecommerceuserapi.dto.UserDTO;
import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapper;
import br.com.rendersonjunior.ecommerceuserapi.model.User;
import br.com.rendersonjunior.ecommerceuserapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

    }

    public UserDTO findById(long userId) {
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(usuario);

    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(userMapper.fromDTO(userDTO));
        return userMapper.toDTO(user);

    }

    public UserDTO delete(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not have record to delete"));
        userRepository.delete(user);
        return userMapper.toDTO(user);

    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return userMapper.toDTO(user);
        }
        return null;

    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

    }

    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not exists"));

        if (Objects.nonNull(userDTO.getNome()) && !user.getNome().equals(userDTO.getNome())) {
            user.setNome(userDTO.getNome());
        }
        if (userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getEmail())) {
            user.setTelefone(userDTO.getTelefone());
        }

        if (userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())) {
            user.setEndereco(userDTO.getEndereco());
        }

        user = userRepository.save(user);
        return userMapper.toDTO(user);

    }

    public Page<UserDTO> getAllPage(Pageable page) {
        return userRepository.findAll(page).map(userMapper::toDTO);

    }

}
