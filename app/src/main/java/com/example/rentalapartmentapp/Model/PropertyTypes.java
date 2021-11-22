package com.example.rentalapartmentapp.Model;

public enum PropertyTypes {
    FLAT, HOUSE, BUNGALOW, VILLA, PEN_HOUSE;

    public static PropertyTypes getPropertyType(String propertyType) {
        switch (propertyType) {
            case "FLAT":
                return PropertyTypes.FLAT;
            case "HOUSE":
                return PropertyTypes.HOUSE;
            case "BUNGALOW":
                return PropertyTypes.BUNGALOW;
            case "VILLA":
                return PropertyTypes.VILLA;
            case "PEN_HOUSE":
                return PropertyTypes.PEN_HOUSE;
            default:
                return PropertyTypes.FLAT;
        }
    }
}
