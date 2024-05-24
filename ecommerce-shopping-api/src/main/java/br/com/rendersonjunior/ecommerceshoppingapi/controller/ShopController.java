package br.com.rendersonjunior.ecommerceshoppingapi.controller;

import br.com.rendersonjunior.ecommerceshoppingapi.mapper.ShopMapper;
import br.com.rendersonjunior.ecommerceshoppingapi.service.shop.IShopService;
import com.rendersonjunior.dto.ShopDTO;
import com.rendersonjunior.dto.ShopReportDTO;
import com.rendersonjunior.dto.ShopRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shopping")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @Autowired
    private ShopMapper mapper;

    @GetMapping
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilter(@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio, @RequestParam(name = "dataFim", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim, @RequestParam(name = "valorMinimo", required = false) BigDecimal valorMinimo, Pageable pageable) {
        return shopService.getShopByFilter(dataInicio, dataFim, valorMinimo, pageable);
    }

    @GetMapping("/report")
    public ShopReportDTO getReportByDate(@RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") final LocalDate dataInicio, @RequestParam(name = "dataFim", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") final LocalDate dataFim) {
        return shopService.getReportByDate(dataInicio, dataFim);
    }

    @GetMapping("/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(@RequestHeader(name = "key", required = true) final String key,
                           @Valid @RequestBody ShopRequestDTO shopRequestDTO) {
        return mapper.toDTO(shopService.save(mapper.fromRequestDTO(shopRequestDTO), key));
    }

}
