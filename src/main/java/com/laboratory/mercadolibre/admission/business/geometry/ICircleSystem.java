package com.laboratory.mercadolibre.admission.business.geometry;

import com.laboratory.mercadolibre.admission.business.geometry.shape.Circle;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Point;

import java.util.List;

public interface ICircleSystem {

    boolean existIntersection(List<Circle> circles);

    List<Point> findPointIntersection(List<Circle> circles);
}
