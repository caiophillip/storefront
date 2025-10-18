package com.example.storefront.repository;

import com.example.storefront.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findByActiveTrueOrderByNameAsc();

}
