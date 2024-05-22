package com.demo.warehousemanagementsystem.exception;

import com.demo.warehousemanagementsystem.common.ResultInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@EqualsAndHashCode(callSuper = true)
public class DatabaseOperationException extends RuntimeException {
    private final ResultInfo resultInfo;

    public DatabaseOperationException(ResultInfo resultInfo) {
        super(resultInfo.getMessage());
        this.resultInfo = resultInfo;
    }
}
