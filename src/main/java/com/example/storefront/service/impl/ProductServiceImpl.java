package com.example.storefront.service.impl;

import com.example.storefront.dto.ProductDetailDTO;
import com.example.storefront.dto.ProductInfoDTO;
import com.example.storefront.entity.ProductEntity;
import com.example.storefront.mapper.IProductMapper;
import com.example.storefront.repository.ProductRepository;
import com.example.storefront.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final RestClient warehouseClient;
    private final IProductMapper mapper;

    @Override
    public ProductEntity save(ProductEntity entity) {
        return productRepository.save(entity);
    }

    @Override
    public void changeActivated(UUID id, boolean active) {
        var entity = findById(id);
        entity.setActive(active);
        productRepository.save(entity);
    }

    @Override
    public List<ProductEntity> findAllActive() {
        return productRepository.findByActiveTrueOrderByNameAsc();
    }

    @Override
    public ProductInfoDTO findInfo(UUID id) {
        var entity = findById(id);
        var price = requestCurrentAmount(id);
        return mapper.toDto(entity, price);
    }

    @Override
    public void purchase(UUID id) {
        purchaseWarehouse(id);
    }

    private ProductEntity findById(final UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    private BigDecimal requestCurrentAmount(UUID id) {
        var dto = warehouseClient.get()
                .uri("/products/" + id)
                .retrieve()
                .body(ProductDetailDTO.class);
        return dto.price();
    }

    private void purchaseWarehouse(final UUID id) {
        var path = String.format("/products/%s/purchase", id);
        warehouseClient.post()
                .uri(path)
                .retrieve()
                .toBodilessEntity();
    }
}
