package com.demo.warehousemanagementsystem.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    CATEGORY_1(1),
    CATEGORY_2(2),
    CATEGORY_3(3),
    CATEGORY_4(4);
    private final int categoryId;
}
