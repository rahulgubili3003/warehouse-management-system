package com.demo.warehousemanagementsystem.controller;

import com.demo.warehousemanagementsystem.application.ProductApplication;
import com.demo.warehousemanagementsystem.dto.ProductRequest;
import com.demo.warehousemanagementsystem.misc.JsonSerialize;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductApplication productApplication;

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
        String jsonString = JsonSerialize.convertResponseToJsonString(product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonString);
    }
}
