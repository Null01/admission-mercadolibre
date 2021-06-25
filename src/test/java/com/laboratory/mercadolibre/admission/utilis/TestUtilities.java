package com.laboratory.mercadolibre.admission.utilis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Circle;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Point;
import com.laboratory.mercadolibre.admission.controller.dto.SatelliteListRequest;
import com.laboratory.mercadolibre.admission.model.entities.Satellite;
import com.laboratory.mercadolibre.admission.model.entities.Spacecraft;

import java.util.Arrays;
import java.util.List;


public class TestUtilities {

    protected String convertObjectToJson(Object target) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(target);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    protected List<Satellite> loadSystemPropertiesSatellite() {
        return Arrays.asList(
                Satellite.builder().name("kenobi").positionX(-500.0).positionY(-200.0).build(),
                Satellite.builder().name("skywalker").positionX(100.0).positionY(-100.0).build(),
                Satellite.builder().name("sato").positionX(500.0).positionY(100.0).build()
        );
    }

    protected List<Satellite> getSatelliteRequestSuccess() {
        return Arrays.asList(
                Satellite.builder().name("kenobi").distance(848.528).messages(new String[]{"este", "", "", "mensaje", ""}).build(),
                Satellite.builder().name("skywalker").distance(500.0).messages(new String[]{"", "es", "", "", "secreto"}).build(),
                Satellite.builder().name("sato").distance(500.0).messages(new String[]{"este", "", "un", "", ""}).build()
        );
    }


    protected List<Circle> getCirclesSuccess() {
        return Arrays.asList(
                Circle.builder().x(-500.0).y(-200.0).build(),
                Circle.builder().x(100.0).y(-100.0).build(),
                Circle.builder().x(500.0).y(100.0).build()
        );
    }

    protected List<Point> getPointsSuccess() {
        return Arrays.asList(
                Point.builder().x(99.99980565333954).y(399.99999999996226).build(),
                Point.builder().x(100.0).y(400.0).build(),
                Point.builder().x(262.1619783207143).y(-572.9730360042865).build(),
                Point.builder().x(500.0).y(-400.0).build()
        );
    }

    protected Spacecraft getSpacecraftSuccess() {
        return Spacecraft.builder().positionX(100.0).positionY(400.0).build();
    }

    protected SatelliteListRequest getSatelliteListRequestSuccess() {
        return SatelliteListRequest.builder().satellites(
                Arrays.asList(
                        new SatelliteListRequest.SatelliteRequest("kenobi", 848.528, new String[]{"este", "", "", "mensaje", ""}),
                        new SatelliteListRequest.SatelliteRequest("skywalker", 500.0, new String[]{"", "es", "", "", "secreto"}),
                        new SatelliteListRequest.SatelliteRequest("sato", 500.0, new String[]{"este", "", "un", "", ""})
                )).build();
    }

    protected String getSatelliteListResponseSuccess() {
        return "{\"status\":\"OK\",\"position\":{\"positionX\":100.0,\"positionY\":400.0}}";
    }
}
