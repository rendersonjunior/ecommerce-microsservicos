package br.com.rendersonjunior.ecommerceproductapi.repository;

import br.com.rendersonjunior.ecommerceproductapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p "
            +  "from Product p "
            +  "join Category c on p.category.id = c.id "
            +  "where c.id = :categoryId")
    List<Product> getProductByCategory(@Param("categoryId") final long categoryId);

    Optional<Product> findByProductIdentifier(String productIdentifier);

}
