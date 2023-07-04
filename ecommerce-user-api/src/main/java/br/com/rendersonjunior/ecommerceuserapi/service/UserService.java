package br.com.rendersonjunior.ecommerceuserapi.service;

import br.com.rendersonjunior.ecommerceuserapi.dto.UserDTO;
import br.com.rendersonjunior.ecommerceuserapi.model.User;
import br.com.rendersonjunior.ecommerceuserapi.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());

    }

    public UserDTO findById(long userId) {
        User usuario = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserDTO.convert(usuario);

    }

    public UserDTO save(UserDTO userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);

    }

    public UserDTO delete(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not have record to delete"));
        userRepository.delete(user);
        return UserDTO.convert(user);

    }

    public UserDTO findByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user != null) {
            return UserDTO.convert(user);
        }
        return null;

    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNomeLike(name);
        return usuarios
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());

    }

    public UserDTO editUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not exists"));
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
        return UserDTO.convert(user);

    }

    public Page<UserDTO> getAllPage(Pageable page) {
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);

    }


}
