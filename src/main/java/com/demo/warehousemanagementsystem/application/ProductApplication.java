package com.demo.warehousemanagementsystem.application;

import com.demo.warehousemanagementsystem.dto.ProductRequest;
import com.demo.warehousemanagementsystem.dto.ProductResponse;
import com.demo.warehousemanagementsystem.entity.Product;
import com.demo.warehousemanagementsystem.exception.DatabaseOperationException;
import com.demo.warehousemanagementsystem.exception.ProductNotFoundException;
import com.demo.warehousemanagementsystem.repository.ProductRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.demo.warehousemanagementsystem.common.ResultInfoConstants.PRODUCT_NOT_FOUND;

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
            final Product product = productRepository.findProductByProductIdAndIsDeleted(productId, false)
                    .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
            return product.getAvailableUnits();
        } catch (Exception e) {
            log.error("Error while getting Stock Data from the DB", e);
            throw new DatabaseOperationException("Error while getting stock details", e);
        }
    }

    public ProductResponse getProduct(@NonNull final Long productId) {
        final var product = productRepository.findProductByProductIdAndIsDeleted(
                productId,
                false
        ).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));

        return ProductResponse
                .builder()
                .productId(productId)
                .productName(product.getProductName())
                .availableUnits(product.getAvailableUnits())
                .build();
    }

    public Product updateStockDetails(@NonNull final ProductRequest productRequest) {
        try {
            final var product = productRepository.findProductByProductIdAndIsDeleted(
                    productRequest.productId(),
                    false
            ).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
            product.setAvailableUnits(productRequest.availableUnits());
            return productRepository.save(product);
        } catch (Exception e) {
            log.error("Error occurred while fetching the product", e);
            throw new DatabaseOperationException("Error while Performing DB operations", e);
        }
    }
}
