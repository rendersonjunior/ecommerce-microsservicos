package br.com.rendersonjunior.ecommerceshoppingapi.service.user;

import com.rendersonjunior.dto.UserDTO;
import com.rendersonjunior.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService implements IUserService {

    @Value("${USER_API_URL:http://localhost:8080}")
    private static String userApiURL;

    @Override
    public UserDTO getUserByCpf(final String cpf,
                                final String key) {
        try {
            final var webClient = WebClient.builder()
                    .baseUrl(userApiURL)
                    .build();

            final var user = webClient.get()
                    .uri("/user/"
                            .concat(cpf)
                            .concat("/cpf?key=")
                            .concat(key))
                    .retrieve()
                    .bodyToMono(UserDTO.class);

            return user.block();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
}
