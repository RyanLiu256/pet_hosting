package edu.guat.po;

/**
 * 宠物品种实体类
 */
public class Breeds {

    private Integer bid;
    private String species;
    private Double breedPrice;
    private String picture;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Double getBreedPrice() {
        return breedPrice;
    }

    public void setBreedPrice(Double breedPrice) {
        this.breedPrice = breedPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Breeds{" +
                "bid=" + bid +
                ", species='" + species + '\'' +
                ", breedPrice=" + breedPrice +
                ", picture='" + picture + '\'' +
                '}';
    }
}
