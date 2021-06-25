package com.laboratory.mercadolibre.admission.service;

import com.laboratory.mercadolibre.admission.exception.BusinessException;
import com.laboratory.mercadolibre.admission.exception.StorageException;
import com.laboratory.mercadolibre.admission.model.entities.Satellite;
import com.laboratory.mercadolibre.admission.model.entities.Spacecraft;

import java.util.List;

public interface ICommunicationSatelliteService {

    Spacecraft getLocation(List<Satellite> satellites) throws BusinessException;

    String getMessage(List<String[]> messages);


    void addSatellite(Satellite satellite) throws StorageException;

    List<Satellite> getSatellite();

}
