package com.laboratory.mercadolibre.admission.service;

import com.laboratory.mercadolibre.admission.business.geometry.impl.CircleSystemImpl;
import com.laboratory.mercadolibre.admission.exception.BusinessException;
import com.laboratory.mercadolibre.admission.model.entities.Spacecraft;
import com.laboratory.mercadolibre.admission.model.repository.SatelliteRepository;
import com.laboratory.mercadolibre.admission.service.impl.CommunicationSatelliteServiceImpl;
import com.laboratory.mercadolibre.admission.service.impl.DecodeCommunicationServiceImpl;
import com.laboratory.mercadolibre.admission.utilis.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommunicationSatelliteServiceImplTest extends TestUtilities {

    @InjectMocks
    private CommunicationSatelliteServiceImpl communicationSatelliteService;

    @Mock
    private SatelliteRepository satelliteRepository;
    @Mock
    private CircleSystemImpl iCircleSystem;
    @Mock
    private DecodeCommunicationServiceImpl iDecodeCommunicationService;

    @Test
    void getLocationSatelliteNotHaveCoordinatesFailed() {
        Assertions.assertThrows(BusinessException.SatelliteWithoutCoordinatesException.class, () -> communicationSatelliteService.getLocation(this.getSatelliteRequestSuccess()));
    }

    @Test
    void getLocationSatelliteNotIntersectWithOtherFailed() {
        Mockito.when(satelliteRepository.getCurrentSatellites()).thenReturn(this.loadSystemPropertiesSatellite());

        Assertions.assertThrows(BusinessException.NotIntersectAllSatellitesException.class, () -> communicationSatelliteService.getLocation(this.getSatelliteRequestSuccess()));
    }

    @Test
    void getLocationSuccess() throws BusinessException {
        Mockito.when(satelliteRepository.getCurrentSatellites()).thenReturn(this.loadSystemPropertiesSatellite());
        Mockito.when(iCircleSystem.existIntersection(Mockito.any())).thenReturn(true);
        Mockito.when(iCircleSystem.findPointIntersection(Mockito.any())).thenReturn(this.getPointsSuccess());

        Spacecraft spacecraft = communicationSatelliteService.getLocation(this.getSatelliteRequestSuccess());
        Assertions.assertNotNull(spacecraft);
        Assertions.assertEquals(100.0, spacecraft.getPositionX());
        Assertions.assertEquals(400.0, spacecraft.getPositionY());
    }

}
