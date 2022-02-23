package com.pdv.springbootkafka.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel {

    private int status;
    private String message;

    public ResponseModel() {
        this.status = 1000;
        this.message = "successful";
    }

    public void unsuccessful() {
        this.status = 1001;
        this.message = "unsuccessful";
    }
}
