package com.demo.warehousemanagementsystem.repository;

import com.demo.warehousemanagementsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductIdAndIsDeleted(final Long productId, final Boolean isDeleted);
}
