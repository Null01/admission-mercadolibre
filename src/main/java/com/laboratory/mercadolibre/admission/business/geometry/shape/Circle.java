package com.laboratory.mercadolibre.admission.business.geometry.shape;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Circle extends Point {
    private double radio;

    public Circle(Double x, Double y, Double r) {
        this.x = x;
        this.y = y;
        this.radio = r;
    }
}
