package com.example.projectstore.awesomeAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@ToString
public class AwesomeAPIObjectDTO {

    @JsonProperty("code")
    private String code;

    @JsonProperty("codein")
    private String codein;

    @JsonProperty("bid")
    private BigDecimal bid;


}
