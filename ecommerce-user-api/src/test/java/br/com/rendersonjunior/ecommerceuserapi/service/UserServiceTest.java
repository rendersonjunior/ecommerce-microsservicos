package br.com.rendersonjunior.ecommerceuserapi.service;

import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapper;
import br.com.rendersonjunior.ecommerceuserapi.model.User;
import br.com.rendersonjunior.ecommerceuserapi.repository.UserRepository;
import br.com.rendersonjunior.ecommerceuserapi.service.user.UserService;
import com.rendersonjunior.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void testListAllUsers() {
        final List<User> users = new ArrayList<>();
        users.add(getUser(1L, "User Name", "123"));
        users.add(getUser(2L, "User Name 2", "321"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        final List<UserDTO> usersReturn = userService.getAll();

        Assertions.assertEquals(2, usersReturn.size());
    }

    @Test
    public void testSaveUser() {
        final var userDB = getUser(1L, "User Name", "123");
        final var userDTO = mapper.toDTO(getUser(1L, "User Name", "123"));

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDB);

        final var user = userService.save(userDTO);

        Assertions.assertEquals(userDTO.getNome(), user.getNome());
        Assertions.assertEquals(userDTO.getCpf(), user.getCpf());
    }

    @Test
    public void testEditUser() {
        final var userDB = getUser(1L, "User Name", "123");
        final var userDTO = mapper.toDTO(getUser(1L, "User Name", "123"));

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userDB));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(userDB);

        userDTO.setEndereco("Novo Endereço");
        userDTO.setTelefone("12345678910");

        final var user = userService.editUser(1L, userDTO);

        Assertions.assertEquals(userDTO.getEndereco(), user.getEndereco());
        Assertions.assertEquals(userDTO.getTelefone(), user.getTelefone());
    }

    public static User getUser(Long id, String nome, String cpf) {
        return User.builder()
                .id(id)
                .nome(nome)
                .cpf(cpf)
                .endereco("Rua do Mock, 8, Cidade do Teste")
                .telefone("5432")
                .build();
    }

}
