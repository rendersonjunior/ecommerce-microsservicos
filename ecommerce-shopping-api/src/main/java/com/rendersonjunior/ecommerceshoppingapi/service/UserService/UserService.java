package com.rendersonjunior.ecommerceshoppingapi.service.UserService;

import com.rendersonjunior.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService implements IUserService {
    private final String userApiURL = "http://localhost:8080";

    @Override
    public UserDTO getUserByCpf(final String cpf) {
        try {
            final var webClient = WebClient.builder()
                    .baseUrl(userApiURL)
                    .build();

            final var user = webClient.get()
                    .uri("/user/".concat(cpf).concat("/cpf"))
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return user.block();
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }
}
