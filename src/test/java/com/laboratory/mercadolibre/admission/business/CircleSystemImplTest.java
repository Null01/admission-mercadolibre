package com.laboratory.mercadolibre.admission.business;

import com.laboratory.mercadolibre.admission.business.geometry.impl.CircleSystemImpl;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Point;
import com.laboratory.mercadolibre.admission.utilis.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class CircleSystemImplTest extends TestUtilities {

    @InjectMocks
    private CircleSystemImpl iCircleSystem;

    @Test
    void existIntersectionSuccess() {
        boolean intersect = iCircleSystem.existIntersection(this.getCirclesSuccess());
        Assertions.assertFalse(intersect);
    }

    @Test
    void findPointIntersectionSuccess() {
        List<Point> points = iCircleSystem.findPointIntersection(this.getCirclesSuccess());
        Assertions.assertNotNull(points);
    }

}
