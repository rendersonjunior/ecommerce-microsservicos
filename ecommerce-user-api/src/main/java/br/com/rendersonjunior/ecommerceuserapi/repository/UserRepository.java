package br.com.rendersonjunior.ecommerceuserapi.repository;

import br.com.rendersonjunior.ecommerceuserapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpfAndKey(final String cpf, final String key);

    List<User> queryByNomeLike(final String name);

}
