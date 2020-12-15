package model;


import lombok.Data;

@Data
public class User {

    private final long id;
    private final String name;


    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
