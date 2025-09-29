package com.pdv.springbootkafka.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


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
