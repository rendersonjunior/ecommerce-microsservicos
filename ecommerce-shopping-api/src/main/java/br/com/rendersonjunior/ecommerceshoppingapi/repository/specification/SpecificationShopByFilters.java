package br.com.rendersonjunior.ecommerceshoppingapi.repository.specification;

import br.com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Builder
public class SpecificationShopByFilters implements Specification<Shop> {
    private final LocalDate dataInicio;
    private final LocalDate dataFim;
    private final BigDecimal valorMinimo;

    @Override
    public Predicate toPredicate(final Root<Shop> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<>();

        if (nonNull(dataInicio)) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date"), dataInicio));
        }

        if (nonNull(dataFim)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("date"), dataFim));
        }

        if (nonNull(valorMinimo)) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("total"), valorMinimo));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
