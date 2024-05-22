package com.demo.warehousemanagementsystem.exception;

import com.demo.warehousemanagementsystem.common.ResultInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor(force = true)
public class ProductNotFoundException extends RuntimeException {
    private final ResultInfo resultInfo;

    public ProductNotFoundException(ResultInfo resultInfo) {
        super(resultInfo.getMessage());
        this.resultInfo = resultInfo;
    }
}
