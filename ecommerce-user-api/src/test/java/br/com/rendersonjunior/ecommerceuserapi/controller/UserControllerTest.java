package br.com.rendersonjunior.ecommerceuserapi.controller;

import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapper;
import br.com.rendersonjunior.ecommerceuserapi.model.User;
import br.com.rendersonjunior.ecommerceuserapi.service.UserServiceTest;
import br.com.rendersonjunior.ecommerceuserapi.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Spy
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
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
