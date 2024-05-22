package com.demo.warehousemanagementsystem.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ResponseEntityBuilder implements Serializable {
    private String message;
    private String status;
    private String value;
}
