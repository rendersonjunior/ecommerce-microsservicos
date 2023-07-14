package br.com.rendersonjunior.ecommerceproductapi.repository;

import br.com.rendersonjunior.ecommerceproductapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
