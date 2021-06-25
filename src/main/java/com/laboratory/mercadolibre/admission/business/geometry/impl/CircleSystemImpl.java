package com.laboratory.mercadolibre.admission.business.geometry.impl;

import com.laboratory.mercadolibre.admission.business.geometry.ICircleSystem;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Circle;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CircleSystemImpl implements ICircleSystem {

    @Override
    public boolean existIntersection(List<Circle> circles) {
        for (var i = 1; i < circles.size(); i++) {
            var a = circles.get(i - 1);
            var b = circles.get(i);
            double distSq = (a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY());
            double radSumSq = (a.getRadio() + b.getRadio()) * (a.getRadio() + b.getRadio());
            if (distSq > radSumSq)
                return false; // Circle not touch to each other.
            // Else: Circle touch to each other - Circle intersect to each other
        }
        return true;
    }

    @Override
    public List<Point> findPointIntersection(List<Circle> circles) {
        List<Point> pointsIntersection = new ArrayList<>();
        for (var i = 1; i < circles.size() && circles.size() > 1; i++) {
            var c1 = circles.get(i - 1);
            var c2 = circles.get(i);
            double d = Math.sqrt(Math.pow((c1.getX() - c2.getX()), 2) + Math.pow((c1.getY() - c2.getY()), 2));
            double l = (Math.pow(c1.getRadio(), 2) - Math.pow(c2.getRadio(), 2) + Math.pow(d, 2)) / (2 * d);
            double h = Math.sqrt(Math.pow(c1.getRadio(), 2) - Math.pow(l, 2));

            double x1 = ((l * (c2.getX() - c1.getX())) / d) + ((h * (c2.getY() - c1.getY())) / d) + c1.getX();
            double x2 = ((l * (c2.getX() - c1.getX())) / d) - ((h * (c2.getY() - c1.getY())) / d) + c1.getX();

            double y1 = ((l * (c2.getY() - c1.getY())) / d) - ((h * (c2.getX() - c1.getX())) / d) + c1.getY();
            double y2 = ((l * (c2.getY() - c1.getY())) / d) + ((h * (c2.getX() - c1.getX())) / d) + c1.getY();
            pointsIntersection.addAll(Arrays.asList(new Point(x1, y1), new Point(x2, y2)));
        }
        return pointsIntersection;
    }
}
