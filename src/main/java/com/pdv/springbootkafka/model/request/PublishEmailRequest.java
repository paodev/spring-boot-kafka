package com.pdv.springbootkafka.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishEmailRequest {

    @NotNull
    @NotEmpty
    private String to;
    @NotNull
    @NotEmpty
    private String payload;
}
