package com.demo.warehousemanagementsystem.controller;

import com.demo.warehousemanagementsystem.application.ProductApplication;
import com.demo.warehousemanagementsystem.dto.ProductRequest;
import com.demo.warehousemanagementsystem.misc.JsonSerialize;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplication productApplication;

    @GetMapping("/getAllProducts")
    public ResponseEntity<String> getAllProducts(@RequestParam(required = false, defaultValue = "") final String sortBy) {
        final var products = productApplication.getAllProducts(sortBy);
        final String jsonstring = JsonSerialize.convertResponseToJsonString(products);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonstring);
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<String> getProductDetails(@NonNull @PathVariable final Long productId) {
        final var stock = productApplication.getProduct(productId);
        String jsonString = JsonSerialize.convertResponseToJsonString(stock);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }

    @GetMapping("/getStock/{productId}")
    public ResponseEntity<String> getStockDetails(@NonNull @PathVariable final Long productId) {
        final var stock = productApplication.getStock(productId);
        String jsonString = JsonSerialize.convertResponseToJsonString(stock);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }

    @PostMapping("/addProducts")
    public ResponseEntity<String> addProducts(@RequestBody final ProductRequest productRequest) {
        log.info("Category Details: {}", productRequest.category());
        final var product = productApplication.addProduct(productRequest);
        final String jsonString = JsonSerialize.convertResponseToJsonString(product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }

    @PutMapping("/updateStockDetails")
    public ResponseEntity<String> updateStockDetails(@RequestBody final ProductRequest productRequest) {
        final var updatedProduct = productApplication.updateStockDetails(productRequest);
        final String jsonString = JsonSerialize.convertResponseToJsonString(updatedProduct);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable final Long productId) {
        final var product = productApplication.deleteProduct(productId);
        final var jsonString = JsonSerialize.convertResponseToJsonString(product);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }
}
