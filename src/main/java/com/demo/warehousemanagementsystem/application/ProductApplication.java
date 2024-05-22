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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.demo.warehousemanagementsystem.common.ResultInfoConstants.PRODUCT_NOT_FOUND;
import static com.demo.warehousemanagementsystem.common.ResultInfoConstants.PRODUCT_SAVE_IN_DB_FAILED;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductApplication {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts(@NonNull final String sortBy) {
        final var products = Objects.equals(sortBy, "") ? productRepository.findAll() : productRepository.findAll(Sort.by(sortBy));
        return products.stream().map(this::productResponseBuilder).toList();
    }

    private ProductResponse productResponseBuilder(@NonNull final Product product) {
        return ProductResponse
                .builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .availableUnits(product.getAvailableUnits())
                .build();
    }

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
            throw new DatabaseOperationException(PRODUCT_SAVE_IN_DB_FAILED);
        }
    }

    public Integer getStock(@NonNull final Long productId) {
        final Product product = productRepository.findProductByProductIdAndIsDeleted(productId, false)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
        return product.getAvailableUnits();
    }

    @Cacheable(value = "productCache", key = "#productId", unless = "#result.availableUnits() > 1")
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
        final var product = productRepository.findProductByProductIdAndIsDeleted(
                productRequest.productId(),
                false
        ).orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));
        product.setAvailableUnits(productRequest.availableUnits());
        return productRepository.save(product);
    }

    public ProductResponse deleteProduct(@NonNull final Long productId) {
        final var product =
                productRepository.
                        findProductByProductIdAndIsDeleted(productId, false)
                        .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND));

        product.setIsDeleted(true);
        try {
            final var savedProduct = productRepository.save(product);
            return ProductResponse
                    .builder()
                    .productId(productId)
                    .productName(savedProduct.getProductName())
                    .availableUnits(savedProduct.getAvailableUnits())
                    .build();
        } catch (Exception e) {
            log.error("Error occurred while saving product in DB", e);
            throw new DatabaseOperationException(PRODUCT_SAVE_IN_DB_FAILED);
        }
    }
}
