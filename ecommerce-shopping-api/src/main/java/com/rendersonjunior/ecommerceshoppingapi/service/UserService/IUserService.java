package com.rendersonjunior.ecommerceshoppingapi.service.UserService;


import com.rendersonjunior.dto.UserDTO;

public interface IUserService {

    UserDTO getUserByCpf(final String cpf);

}
