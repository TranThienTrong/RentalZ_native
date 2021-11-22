package com.example.rentalapartmentapp.Model;

public enum BedroomTypes {
    NONE, STUDIO, ONE, TWO, THREE, MORE_THAN_THREE;

    public static BedroomTypes getBedroom(String bedroomsType) {
        switch (bedroomsType) {
            case "NONE":
                return BedroomTypes.NONE;
            case "STUDIO":
                return BedroomTypes.STUDIO;
            case "ONE":
                return BedroomTypes.ONE;
            case "TWO":
                return BedroomTypes.TWO;
            case "THREE":
                return BedroomTypes.THREE;
            case "MORE_THAN_THREE":
                return BedroomTypes.MORE_THAN_THREE;
            default:
                return BedroomTypes.NONE;
        }
    }
}
