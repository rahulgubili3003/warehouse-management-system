package com.demo.warehousemanagementsystem.repository;

import com.demo.warehousemanagementsystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Optional<Product> findProductByProductIdAndIsDeleted(final Long productId, final Boolean isDeleted);
    //List<Product> findProductByIsDeleted(final Boolean isDeleted);
}
