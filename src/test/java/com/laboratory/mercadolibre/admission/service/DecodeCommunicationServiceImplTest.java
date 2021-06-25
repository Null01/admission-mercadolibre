package com.laboratory.mercadolibre.admission.service;

import com.laboratory.mercadolibre.admission.model.entities.Satellite;
import com.laboratory.mercadolibre.admission.service.impl.DecodeCommunicationServiceImpl;
import com.laboratory.mercadolibre.admission.utilis.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class DecodeCommunicationServiceImplTest extends TestUtilities {

    @InjectMocks
    private DecodeCommunicationServiceImpl decodeCommunicationService;

    @Test
    void decodeMessageSuccess() {
        List<String[]> messages = this.getSatelliteRequestSuccess().stream().map(Satellite::getMessages).collect(Collectors.toList());
        String message = decodeCommunicationService.decodeMessage(messages);
        Assertions.assertNotNull(message);
        Assertions.assertEquals("este es un mensaje secreto", message);
    }

    @Test
    void decodeMessageFailed() {
        List<String[]> messages = Arrays.asList(
                new String[]{"test", "", "hola", "mundo", "", "en", "", "casa"},
                new String[]{"mundo", "colombia", "", "la", "casa"}
        );
        String message = decodeCommunicationService.decodeMessage(messages);
        Assertions.assertNotNull(message);
        Assertions.assertNotEquals("test ok hola mundo colombia en la casa", message);
    }


}
