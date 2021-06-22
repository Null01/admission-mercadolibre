package com.laboratory.mercadolibre.admission.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Spacecraft extends Satellite {

    public Spacecraft(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
