package com.demo.warehousemanagementsystem.application;

import com.demo.warehousemanagementsystem.dto.ProductRequest;
import com.demo.warehousemanagementsystem.entity.Product;
import com.demo.warehousemanagementsystem.enums.Category;
import com.demo.warehousemanagementsystem.exception.DatabaseOperationException;
import com.demo.warehousemanagementsystem.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductApplication {

    private final ProductRepository productRepository;

    public Product addProduct(final ProductRequest productRequest) {
        final Product product = Product.builder()
                .availableUnits(productRequest.availableUnits())
                .productId(productRequest.productId())
                .productName(productRequest.productName())
                .category(productRequest.category())
                .isDeleted(false)
                .build();
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            log.error("Could not save product in the DB", e);
            throw new DatabaseOperationException("Error adding Product to the DB", e);
        }
    }

    public Integer getStock(@NonNull final Long productId) {
        try {
            final var product = productRepository.findProductByProductIdAndIsDeleted(productId, false);
            return product.getAvailableUnits();
        } catch (Exception e) {
            log.error("Error while getting Stock Data from the DB", e);
            throw new DatabaseOperationException("Error while getting stock details", e);
        }
    }

    public Product getProduct(@NonNull final Long productId) {
        try {
            return productRepository.findProductByProductIdAndIsDeleted(productId, false);
        } catch (Exception e) {
            log.error("Error", e);
            throw new DatabaseOperationException("Error while getting Product details from the DB", e);
        }
    }
}
