package br.com.rendersonjunior.ecommerceshoppingapi.service.shop;

import br.com.rendersonjunior.ecommerceshoppingapi.mapper.ShopMapper;
import br.com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import br.com.rendersonjunior.ecommerceshoppingapi.repository.ReportRepository;
import br.com.rendersonjunior.ecommerceshoppingapi.repository.ShopRepository;
import br.com.rendersonjunior.ecommerceshoppingapi.repository.specification.SpecificationShopByFilters;
import br.com.rendersonjunior.ecommerceshoppingapi.service.product.ProductService;
import br.com.rendersonjunior.ecommerceshoppingapi.service.user.UserService;
import com.rendersonjunior.dto.ItemDTO;
import com.rendersonjunior.dto.ShopDTO;
import com.rendersonjunior.dto.ShopReportDTO;
import com.rendersonjunior.dto.ShopRequestDTO;
import com.rendersonjunior.exception.ProductNotFoundException;
import com.rendersonjunior.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopMapper shopMapper;

    public List<ShopDTO> getAll() {
        return shopRepository.findAll()
                .stream()
                .sorted()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(final String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(final ShopDTO shopDTO) {
        return shopRepository.findAllByDateGreaterThan(shopDTO.getDate())
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByValue(final BigDecimal value) {
        return shopRepository.findAllByTotalGreaterThan(value)
                .stream()
                .map(shopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(final Long shopId) {
        Optional<Shop> shop = shopRepository.findById(shopId);
        return shopMapper.toDTO(shop.orElseThrow(null));
    }

    public ShopDTO save(final ShopRequestDTO shopRequestDTO, final String key) {

        if (isNull(userService.getUserByCpf(shopRequestDTO.getUserIdentifier(), key))) {
            throw new UserNotFoundException();
        }

        final var isProductIsValid = validateProducts(shopRequestDTO.getItems());
        if (isFalse(isProductIsValid)) {
            throw new ProductNotFoundException();
        }

        shopRequestDTO.setTotal(shopRequestDTO.getItems()
                .stream()
                .map(ItemDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        shopRequestDTO.setDate(LocalDateTime.now());
        return shopMapper.toDTO(shopRepository.save(shopMapper.fromRequestDTO(shopRequestDTO)));
    }

    private boolean validateProducts(final List<ItemDTO> items) {

        for (final var item : items) {
            final var productDTO = this.productService.getProductByIdentifier(item.getProductIdentifier());
            if (isNull(productDTO)) {
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }

    public List<ShopDTO> getShopByFilter(final LocalDate dataInicio,
                                         final LocalDate dataFim,
                                         final BigDecimal valorMinimo,
                                         final Pageable pageable) {
        return shopRepository.findAll(SpecificationShopByFilters
                .builder()
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .valorMinimo(valorMinimo)
                .build(), pageable).stream().map(shopMapper::toDTO).collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(final LocalDate dataInicio,
                                         final LocalDate dataFim) {
        return reportRepository.getReportByDate(dataInicio.atTime(0, 0, 0),
                dataFim.atTime(23, 59, 59));
    }

}