package br.com.rendersonjunior.ecommerceuserapi.controller;

import br.com.rendersonjunior.ecommerceuserapi.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<>();

    @PostConstruct
    public void initializeList() {
        UserDTO userDTO = new UserDTO("Eduardo", "123", "Rua a", "eduardo@email.com", "1234-3454", LocalDateTime.now());
        UserDTO userDTO2 = new UserDTO("Luiz", "456", "Rua b", "luiz@email.com", "1234-3454", LocalDateTime.now());
        UserDTO userDTO3 = new UserDTO("Bruna", "678", "Rua c", "bruna@email.com", "1234-3454", LocalDateTime.now());

        usuarios.add(userDTO);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
	usuarios.add(userDTO3);
    }

    @GetMapping
    public List<UserDTO> getUsuarios() {
        return usuarios; 
    }
}
