package com.demo.warehousemanagementsystem.dto;

import com.demo.warehousemanagementsystem.enums.Category;

public record ProductRequest(Long productId, String productName, Category category, Integer availableUnits) {
}
