package br.com.rendersonjunior.ecommerceuserapi.controller;

import br.com.rendersonjunior.ecommerceuserapi.mapper.UserMapper;
import br.com.rendersonjunior.ecommerceuserapi.service.user.IUserService;
import com.rendersonjunior.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final UserMapper mapper;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") final Long id) {
        return userService.findById(id);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(@PathVariable("cpf") final String cpf,
                             @RequestParam(name = "key", required = true) final String key) {
        return userService.findByCpf(cpf, key);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true) String nome) {
        return userService.queryByName(nome);
    }

    @GetMapping("/pageable")
    public Page<UserDTO> getUsersPage(Pageable pageable) {
        return userService.getAllPage(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PatchMapping("/{id}")
    public UserDTO editUser(@PathVariable("id") Long id,
                            @RequestBody UserDTO userDTO) {
        return userService.editUser(id, userDTO);
    }

}
