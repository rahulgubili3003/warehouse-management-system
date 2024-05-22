package com.demo.warehousemanagementsystem.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultInfoConstants {
    public static final ResultInfo PRODUCT_NOT_FOUND = new ResultInfo("Product Not Found in DB", "001", HttpStatus.BAD_REQUEST);
    public static final ResultInfo PRODUCT_SAVE_IN_DB_FAILED = new ResultInfo("Could not save Product in DB", "002", HttpStatus.INTERNAL_SERVER_ERROR);
}
