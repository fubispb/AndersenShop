package model;

import lombok.Data;

@Data
public class Product implements Comparable<Product> {

    private Long id;
    private String name;
    private int price;
    private String country;


    public Product(long id, String name, int price, String country) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.country = country;
    }

    @Override
    public int compareTo(Product o) {
        return this.id.compareTo(o.id);
    }
}
