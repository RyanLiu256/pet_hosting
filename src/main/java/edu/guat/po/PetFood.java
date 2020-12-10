package edu.guat.po;

public class PetFood {

    private Integer pid;
    private String brand;
    private Double price;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PetFood{" +
                "pid=" + pid +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
