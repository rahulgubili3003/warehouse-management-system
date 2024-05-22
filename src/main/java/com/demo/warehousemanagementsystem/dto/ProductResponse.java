package com.demo.warehousemanagementsystem.dto;

import com.demo.warehousemanagementsystem.enums.Category;

public record ProductResponse(Long productId, String productName, Category category, Integer availableUnits) {
}
