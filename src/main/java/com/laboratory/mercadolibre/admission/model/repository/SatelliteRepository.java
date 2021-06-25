package com.laboratory.mercadolibre.admission.model.repository;

import com.laboratory.mercadolibre.admission.exception.StorageException;
import com.laboratory.mercadolibre.admission.model.entities.Satellite;
import com.laboratory.mercadolibre.admission.service.properties.InitializeSystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SatelliteRepository {

    @Autowired
    private InitializeSystemProperties initializeSystemProperties;

    @Cacheable(value = "satellites")
    public List<Satellite> getCurrentSatellites() {
        return initializeSystemProperties.getSatellite();
    }

    @CachePut(value = "satellites", key = "#satellite.name")
    public void addSatellite(Satellite satellite) throws StorageException {
        var currentSatellite = getCurrentSatellites().stream()
                .filter(f -> f.getName().compareToIgnoreCase(satellite.getName()) == 0)
                .findFirst().orElseThrow(StorageException.SatelliteNotFoundException::new);
        currentSatellite.setDistance(satellite.getDistance());
        currentSatellite.setMessages(satellite.getMessages());
    }
}
