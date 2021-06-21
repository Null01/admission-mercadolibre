package com.laboratory.mercadolibre.admission.model;

import com.laboratory.mercadolibre.admission.controller.dto.SatelliteListRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Satellite {
    protected String name;
    protected Double positionX;
    protected Double positionY;
    protected Double distance;
    protected String[] messages;

    public Satellite(SatelliteListRequest.SatelliteRequest request) {
        this.name = request.getName();
        this.distance = request.getDistance();
        this.messages = request.getMessage();
    }
}
