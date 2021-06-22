package com.laboratory.mercadolibre.admission.controller;

import com.laboratory.mercadolibre.admission.service.impl.CommunicationSatelliteServiceImpl;
import com.laboratory.mercadolibre.admission.utilis.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class SatelliteControllerTest extends TestUtilities {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunicationSatelliteServiceImpl communicationSatelliteService;

    @Test
    void findLocationTopSecretSuccess() throws Exception {
        Mockito.when(communicationSatelliteService.getLocation(Mockito.any())).thenReturn(this.getSpacecraftSuccess());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/topsecret")
                        .content(this.convertObjectToJson(this.getSatelliteListRequestSuccess()))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Assertions.assertEquals(this.getSatelliteListResponseSuccess(), result.getResponse().getContentAsString());
    }

}
