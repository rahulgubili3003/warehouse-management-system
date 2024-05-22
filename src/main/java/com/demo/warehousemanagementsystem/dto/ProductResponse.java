package com.demo.warehousemanagementsystem.dto;

import com.demo.warehousemanagementsystem.enums.Category;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record ProductResponse(Long productId, String productName, Category category, Integer availableUnits) {
}
