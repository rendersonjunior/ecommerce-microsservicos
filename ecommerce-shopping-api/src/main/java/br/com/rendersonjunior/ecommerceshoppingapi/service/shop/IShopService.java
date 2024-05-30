package br.com.rendersonjunior.ecommerceshoppingapi.service.shop;

import br.com.rendersonjunior.ecommerceshoppingapi.model.Shop;
import com.rendersonjunior.dto.ShopDTO;
import com.rendersonjunior.dto.ShopReportDTO;
import com.rendersonjunior.dto.ShopRequestDTO;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IShopService {
    public List<ShopDTO> getAll();

    public List<ShopDTO> getByUser(final String userIdentifier);

    public List<ShopDTO> getByDate(final ShopDTO shopDTO);

    public List<ShopDTO> getByValue(final BigDecimal value);

    public ShopDTO findById(final Long shopId);

    public ShopDTO save(final ShopRequestDTO shopRequestDTO, final String key);

    public List<ShopDTO> getShopByFilter(final LocalDate dataInicio,
                                         final LocalDate dataFim,
                                         final BigDecimal valorMinimo,
                                         final Pageable pageable);

    public ShopReportDTO getReportByDate(final LocalDate dataInicio,
                                         final LocalDate dataFim);

}
