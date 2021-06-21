package com.laboratory.mercadolibre.admission.model;

import lombok.Data;

@Data
public class Spacecraft extends Satellite {

    public Spacecraft(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
