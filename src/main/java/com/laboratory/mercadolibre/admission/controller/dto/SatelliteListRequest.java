package com.laboratory.mercadolibre.admission.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SatelliteListRequest {
    private List<SatelliteRequest> satellites;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SatelliteRequest {
        private String name;
        private Double distance;
        private String[] message;
    }

}
