package com.laboratory.mercadolibre.admission.service;

import com.laboratory.mercadolibre.admission.exception.BusinessException;
import com.laboratory.mercadolibre.admission.model.Satellite;
import com.laboratory.mercadolibre.admission.model.Spacecraft;

import java.util.List;

public interface ICommunicationSatelliteService {

    Spacecraft getLocation(List<Satellite> satellites) throws BusinessException;

    String getMessage(List<String[]> messages);

}
