package br.com.rendersonjunior.ecommerceshoppingapi.service;

import br.com.rendersonjunior.ecommerceshoppingapi.mapper.ShopMapper;
import br.com.rendersonjunior.ecommerceshoppingapi.repository.ShopRepository;
import br.com.rendersonjunior.ecommerceshoppingapi.service.product.IProductService;
import br.com.rendersonjunior.ecommerceshoppingapi.service.shop.IShopService;
import br.com.rendersonjunior.ecommerceshoppingapi.service.user.IUserService;
import com.rendersonjunior.dto.ItemDTO;
import com.rendersonjunior.dto.ProductDTO;
import com.rendersonjunior.dto.ShopRequestDTO;
import com.rendersonjunior.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ShopServiceTest {

    @InjectMocks
    private IShopService shopService;

    @Mock
    private IUserService userService;

    @Mock
    private IProductService productService;

    @Mock
    private ShopRepository shopRepository;

    @Spy
    private ShopMapper mapper = Mappers.getMapper(ShopMapper.class);

    @Test
    public void test_saveShop() {
        final var itemDTO = ItemDTO.builder()
                .productIdentifier("123")
                .price(BigDecimal.valueOf(100))
                .build();

        final var shopRequestDTO = ShopRequestDTO.builder()
                .userIdentifier("123")
                .items(new ArrayList<>())
                .total(BigDecimal.valueOf(100))
                .build();
        shopRequestDTO.getItems().add(itemDTO);

        final var productDTO = ProductDTO.builder()
                .productIdentifier("123")
                .preco(BigDecimal.valueOf(100))
                .build();

        Mockito.when(userService.getUserByCpf("123", "123")).thenReturn(new UserDTO());
        Mockito.when(productService.getProductByIdentifier("123")).thenReturn(productDTO);
        Mockito.when(shopRepository.save(Mockito.any())).thenReturn(mapper.fromRequestDTO(shopRequestDTO));

        final var shopDTO = mapper.fromDTO(shopService.save(shopRequestDTO, "123"));

        Assertions.assertEquals(itemDTO.getPrice(), shopDTO.getTotal());
        Assertions.assertEquals(1, shopRequestDTO.getItems().size());
        Mockito.verify(shopRepository, Mockito.times(1)).save(Mockito.any());
    }

}
