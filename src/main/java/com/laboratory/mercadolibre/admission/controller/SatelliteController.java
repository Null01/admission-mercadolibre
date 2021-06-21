package com.laboratory.mercadolibre.admission.controller;

import com.laboratory.mercadolibre.admission.controller.dto.SatelliteListRequest;
import com.laboratory.mercadolibre.admission.controller.dto.SatelliteResponse;
import com.laboratory.mercadolibre.admission.exception.BusinessException;
import com.laboratory.mercadolibre.admission.model.Satellite;
import com.laboratory.mercadolibre.admission.model.Spacecraft;
import com.laboratory.mercadolibre.admission.service.ICommunicationSatelliteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "topsecret")
public class SatelliteController {

    @Autowired
    private ICommunicationSatelliteService iCommunicationSatelliteService;

    @PostMapping
    public ResponseEntity<SatelliteResponse> findLocationTopSecret(@Validated @RequestBody SatelliteListRequest request) throws BusinessException {
        List<Satellite> satellites = request.getSatellites().stream().map(Satellite::new).collect(Collectors.toList());
        Spacecraft spacecraft = iCommunicationSatelliteService.getLocation(satellites);
        String message = iCommunicationSatelliteService.getMessage(satellites.stream().map(Satellite::getMessages).collect(Collectors.toList()));
        return ResponseEntity.ok(SatelliteResponse.builder()
                .status(HttpStatus.OK)
                .position(spacecraft)
                .message(message).build());
    }

}
