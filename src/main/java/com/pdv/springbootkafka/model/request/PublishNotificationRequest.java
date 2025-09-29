package com.pdv.springbootkafka.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


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
