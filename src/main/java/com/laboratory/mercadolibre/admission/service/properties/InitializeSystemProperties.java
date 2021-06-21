package com.laboratory.mercadolibre.admission.service.properties;

import com.laboratory.mercadolibre.admission.model.Satellite;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "data")
public class InitializeSystemProperties {
    private List<Satellite> satellite;
}
