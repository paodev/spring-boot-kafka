package com.pdv.springbootkafka.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishNotificationRequest {

    @NotNull
    @NotBlank
    private String token;
    @NotNull
    @NotBlank
    private String deviceId;
    @NotNull
    @NotBlank
    private String message;
}
