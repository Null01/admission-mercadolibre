package com.laboratory.mercadolibre.admission.model.entities;

import com.laboratory.mercadolibre.admission.controller.dto.SatelliteListRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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
