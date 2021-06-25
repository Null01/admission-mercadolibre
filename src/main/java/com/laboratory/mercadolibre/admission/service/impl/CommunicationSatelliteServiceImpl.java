package com.laboratory.mercadolibre.admission.service.impl;

import com.laboratory.mercadolibre.admission.business.geometry.ICircleSystem;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Circle;
import com.laboratory.mercadolibre.admission.business.geometry.shape.Point;
import com.laboratory.mercadolibre.admission.exception.BusinessException;
import com.laboratory.mercadolibre.admission.exception.StorageException;
import com.laboratory.mercadolibre.admission.model.entities.Satellite;
import com.laboratory.mercadolibre.admission.model.entities.Spacecraft;
import com.laboratory.mercadolibre.admission.model.repository.SatelliteRepository;
import com.laboratory.mercadolibre.admission.service.ICommunicationSatelliteService;
import com.laboratory.mercadolibre.admission.service.IDecodeCommunicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommunicationSatelliteServiceImpl implements ICommunicationSatelliteService {

    @Autowired
    private SatelliteRepository satelliteRepository;

    @Autowired
    private ICircleSystem iCircleSystem;
    @Autowired
    private IDecodeCommunicationService iDecodeCommunicationService;

    @Override
    public Spacecraft getLocation(List<Satellite> satellites) throws BusinessException {
        validateData(satellites);
        log.info("satellites:{}", satellites);
        List<Circle> circles = satellites.stream().map(m -> new Circle(m.getPositionX(), m.getPositionY(), m.getDistance())).collect(Collectors.toList());
        boolean intersect = iCircleSystem.existIntersection(circles);
        if (intersect) {
            Spacecraft spacecraft = null;
            List<Point> points = iCircleSystem.findPointIntersection(circles)
                    .stream().sorted(Comparator.comparing(Point::getX)).collect(Collectors.toList());
            for (var i = 1; i < points.size() && points.size() > 1; i++) {
                log.info("position-between: {} - {}", points.get(i - 1), points.get(i));
                if (points.get(i).getX() - points.get(i - 1).getX() <= 1.0 && points.get(i).getY() - points.get(i - 1).getY() <= 1.0)
                    spacecraft = new Spacecraft(points.get(i).getX(), points.get(i).getY());
            }
            return Optional.ofNullable(spacecraft).orElseThrow(BusinessException.ImpossibleTriangulateLocationException::new);
        }
        throw new BusinessException.NotIntersectAllSatellitesException();
    }

    @Override
    public String getMessage(List<String[]> messages) {
        messages.forEach(m -> log.info("message:{}", Arrays.toString(m)));
        return iDecodeCommunicationService.decodeMessage(messages);
    }


    @Override
    public void addSatellite(Satellite satellite) throws StorageException {
        satelliteRepository.addSatellite(satellite);
    }

    @Override
    public List<Satellite> getSatellite() {
        return satelliteRepository.getCurrentSatellites();
    }

    private void validateData(List<Satellite> satellites) throws BusinessException {
        final List<Satellite> satellitesData = satelliteRepository.getCurrentSatellites();
        for (Satellite satellite : satellites) {
            Satellite s = satellitesData.stream().filter(f -> f.getName().compareToIgnoreCase(satellite.getName()) == 0)
                    .findFirst().orElseThrow(BusinessException.SatelliteWithoutCoordinatesException::new);
            satellite.setPositionX(s.getPositionX());
            satellite.setPositionY(s.getPositionY());
        }
    }
}
