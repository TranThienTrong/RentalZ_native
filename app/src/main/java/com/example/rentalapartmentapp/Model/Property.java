package com.example.rentalapartmentapp.Model;

import java.util.Arrays;

public class Property {
    int id;
    byte[] image;
    String title;
    int roomsQuantity;
    String price;
    String desc;
    String address;
    PropertyTypes propertyType;
    FurnitureTypes furnitureType;
    BedroomTypes bedroom;

    public Property() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PropertyTypes getPropertyType() {
        return propertyType;
    }




    public void setPropertyType(PropertyTypes propertyType) {


        this.propertyType = propertyType;
    }

    public FurnitureTypes getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(FurnitureTypes furnitureType) {
        this.furnitureType = furnitureType;
    }

    public BedroomTypes getBedroom() {
        return bedroom;
    }

    public void setBedroom(BedroomTypes bedroom) {
        this.bedroom = bedroom;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Property(int id, byte[] image, String title, int roomsQuantity, String price, String desc, String address, BedroomTypes bedroomType, FurnitureTypes furnitureType, PropertyTypes propertyType) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.roomsQuantity = roomsQuantity;
        this.price = price;
        this.desc = desc;
        this.address = address;
        this.bedroom = bedroomType;
        this.furnitureType = furnitureType;
        this.propertyType = propertyType;
    }


    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                ", title='" + title + '\'' +
                ", roomsQuantity=" + roomsQuantity +
                ", price='" + price + '\'' +
                ", desc='" + desc + '\'' +
                ", address='" + address + '\'' +
                ", propertyType=" + propertyType +
                ", furnitureType=" + furnitureType +
                ", bedroom=" + bedroom +
                '}';
    }
}
