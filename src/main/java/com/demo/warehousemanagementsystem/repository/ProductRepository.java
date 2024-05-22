package com.demo.warehousemanagementsystem.repository;

import com.demo.warehousemanagementsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByProductIdAndIsDeleted(final Long productId, final Boolean isDeleted);
}
