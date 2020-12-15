package entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "countries")
public class CountryEntity extends AbstractIdentifiableEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "id")
    private List<ProductEntity> products;


}
