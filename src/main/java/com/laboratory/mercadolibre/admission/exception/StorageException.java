package com.laboratory.mercadolibre.admission.exception;

public class StorageException extends Exception {

    public StorageException(String message) {
        super(message);
    }

    public static class SatelliteNotFoundException extends StorageException {
        public SatelliteNotFoundException() {
            super("Satellite not found");
        }
    }


}
