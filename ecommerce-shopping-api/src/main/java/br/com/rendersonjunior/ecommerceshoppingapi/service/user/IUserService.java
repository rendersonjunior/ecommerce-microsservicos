package br.com.rendersonjunior.ecommerceshoppingapi.service.user;

import com.rendersonjunior.dto.UserDTO;

public interface IUserService {

    UserDTO getUserByCpf(final String cpf, final String key);

}
