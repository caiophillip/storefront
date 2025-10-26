package com.example.storefront.service;

import com.example.storefront.dto.ProductInfoDTO;
import com.example.storefront.entity.ProductEntity;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    ProductEntity save(final ProductEntity entity);

    void changeActivated(final UUID id, final boolean active);

    List<ProductEntity> findAllActive();

    ProductInfoDTO findInfo(final UUID id);

    void purchase(final UUID id);

}