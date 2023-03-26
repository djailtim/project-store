package com.example.projectstore.clients.awesome;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class AwesomeListDTO {
    private List<AwesomeDataDTO> awesomeDataDTOList;
}
