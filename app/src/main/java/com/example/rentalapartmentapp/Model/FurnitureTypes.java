package com.example.rentalapartmentapp.Model;

public enum FurnitureTypes {
    NONE, FURNISHED, PART_FURNISHED;

    public static FurnitureTypes getFurnitureTypes(String furnitureTypes) {
        switch (furnitureTypes) {
            case "NONE":
                return FurnitureTypes.NONE;
            case "FURNISHED":
                return FurnitureTypes.FURNISHED;
            case "PART_FURNISHED":
                return FurnitureTypes.PART_FURNISHED;
            default:
                return FurnitureTypes.NONE;
        }
    }
}
