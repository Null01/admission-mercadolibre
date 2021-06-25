package com.laboratory.mercadolibre.admission.controller;

import com.laboratory.mercadolibre.admission.controller.dto.SatelliteListRequest;
import com.laboratory.mercadolibre.admission.controller.dto.SatelliteResponse;
import com.laboratory.mercadolibre.admission.exception.BusinessException;
import com.laboratory.mercadolibre.admission.exception.StorageException;
import com.laboratory.mercadolibre.admission.model.entities.Satellite;
import com.laboratory.mercadolibre.admission.service.ICommunicationSatelliteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class SatelliteController {

    @Autowired
    private ICommunicationSatelliteService iCommunicationSatelliteService;

    @PostMapping(path = "/topsecret")
    public ResponseEntity<SatelliteResponse> getLocationTopSecret(@Validated @RequestBody SatelliteListRequest request) throws BusinessException {
        var satellites = request.getSatellites().stream().map(Satellite::new).collect(Collectors.toList());
        var spacecraft = iCommunicationSatelliteService.getLocation(satellites);
        var message = iCommunicationSatelliteService.getMessage(satellites.stream().map(Satellite::getMessages).collect(Collectors.toList()));
        return ResponseEntity.ok(SatelliteResponse.builder()
                .status(HttpStatus.OK)
                .position(spacecraft)
                .message(message).build());
    }

    @GetMapping(path = "/topsecret_split")
    public ResponseEntity<SatelliteResponse> getLocationTopSecretBySplit() throws BusinessException {
        var satellites = new ArrayList<>(iCommunicationSatelliteService.getSatellite());
        var spacecraft = iCommunicationSatelliteService.getLocation(satellites);
        var message = iCommunicationSatelliteService.getMessage(satellites.stream().map(Satellite::getMessages).collect(Collectors.toList()));
        return ResponseEntity.ok(SatelliteResponse.builder()
                .status(HttpStatus.OK)
                .position(spacecraft)
                .message(message).build());
    }

    @PostMapping(path = "/topsecret_split/{satellite_name}")
    public ResponseEntity<SatelliteResponse> addSatellite(@PathVariable(name = "satellite_name") String satelliteName,
                                                          @Validated @RequestBody SatelliteListRequest.SatelliteRequest request) throws StorageException {
        request.setName(satelliteName);
        iCommunicationSatelliteService.addSatellite(new Satellite(request));
        return ResponseEntity.ok(SatelliteResponse.builder()
                .status(HttpStatus.OK).build());
    }

    @GetMapping(path = "/topsecret")
    public ResponseEntity<SatelliteResponse> getAllSatellites() {
        var satellites = iCommunicationSatelliteService.getSatellite();
        return ResponseEntity.ok(SatelliteResponse.builder()
                .status(HttpStatus.OK)
                .satellites(satellites).build());
    }

}
