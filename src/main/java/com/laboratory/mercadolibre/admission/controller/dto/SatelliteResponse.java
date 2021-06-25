package com.laboratory.mercadolibre.admission.controller.dto;

import com.laboratory.mercadolibre.admission.model.entities.Satellite;
import com.laboratory.mercadolibre.admission.model.entities.Spacecraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteResponse {
    private HttpStatus status;
    private List<Satellite> satellites;
    private Spacecraft position;
    private String message;
}
