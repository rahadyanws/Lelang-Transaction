package com.rahadyan.lelangtransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseWrapper {
    private String message;
    private Object data;
    private int httpStatus;
    private List<String> errors;
}
