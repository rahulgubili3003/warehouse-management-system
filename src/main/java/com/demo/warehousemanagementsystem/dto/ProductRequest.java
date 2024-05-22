package com.demo.warehousemanagementsystem.dto;

import com.demo.warehousemanagementsystem.enums.Category;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ProductRequest(Long productId, String productName, Category category, Integer availableUnits) {
}
