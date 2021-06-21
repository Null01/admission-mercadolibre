package com.laboratory.mercadolibre.admission.exception;

public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public static class SatelliteWithoutCoordinatesException extends BusinessException {
        public SatelliteWithoutCoordinatesException() {
            super("Satellite not have coordinates");
        }
    }

    public static class NotIntersectAllSatellitesException extends BusinessException {
        public NotIntersectAllSatellitesException() {
            super("Not intersect all satellites");
        }
    }

    public static class ImpossibleTriangulateLocationException extends BusinessException {
        public ImpossibleTriangulateLocationException() {
            super("Impossible to triangulate the location of spacecraft");
        }
    }

}
