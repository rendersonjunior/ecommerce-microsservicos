package br.com.rendersonjunior.ecommerceuserapi.controller;

import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapper;
import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapperImpl;
import br.com.rendersonjunior.ecommerceuserapi.model.User;
import br.com.rendersonjunior.ecommerceuserapi.service.UserServiceTest;
import br.com.rendersonjunior.ecommerceuserapi.service.user.UserService;
import com.rendersonjunior.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    private UserMapper mapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mapper = new UserMapperImpl();
    }

    @Test
    public void testListUsers() throws Exception {
        final List<User> users = new ArrayList<>();
        users.add(UserServiceTest.getUser(1L, "Nome1", "123"));

        Mockito.when(userService.getAll()).thenReturn(users);

        final MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final String resp = result.getResponse().getContentAsString();
        final String expected = "[{\"nome\":\"Nome1\",\"cpf\":\"123\",\"endereco\":\"Rua do Mock, 8, Cidade do Teste\",\"key\":null,\"email\":null,\"telefone\":\"5432\",\"dataCadastro\":null}]";
        Assertions.assertEquals(expected, resp);
    }

}
