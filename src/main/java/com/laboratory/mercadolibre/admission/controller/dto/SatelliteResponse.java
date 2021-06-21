package com.laboratory.mercadolibre.admission.controller.dto;

import com.laboratory.mercadolibre.admission.model.Spacecraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteResponse {
    private HttpStatus status;
    private Spacecraft position;
    private String message;
}
