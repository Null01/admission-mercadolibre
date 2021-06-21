package com.laboratory.mercadolibre.admission.service;

import java.util.List;

public interface IDecodeCommunicationService {

    String decodeMessage(List<String[]> message);
}
