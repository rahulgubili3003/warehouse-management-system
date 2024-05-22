package com.demo.warehousemanagementsystem.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public record ProductResponse(Long productId, String productName, Integer availableUnits) implements Serializable {
}
