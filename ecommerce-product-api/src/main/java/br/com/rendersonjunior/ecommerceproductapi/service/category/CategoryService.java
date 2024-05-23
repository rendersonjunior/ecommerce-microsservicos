package br.com.rendersonjunior.ecommerceproductapi.service.category;

import br.com.rendersonjunior.ecommerceproductapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository repository;

    CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
