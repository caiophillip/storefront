package com.example.storefront.mapper;

import com.example.storefront.dto.ProductInfoDTO;
import com.example.storefront.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface IProductMapper {

    ProductInfoDTO toDto(final ProductEntity entity, final BigDecimal price);

}
