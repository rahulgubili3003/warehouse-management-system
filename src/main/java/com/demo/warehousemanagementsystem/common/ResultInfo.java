package com.demo.warehousemanagementsystem.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class ResultInfo implements Serializable {
    private String message;
    private String code;
    private HttpStatus status;
}
