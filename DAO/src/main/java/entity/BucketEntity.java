package entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "buckets")
public class BucketEntity extends AbstractIdentifiableEntity{

    @Column
    private Long users_id;

    @Column
    private Long products_id;

    @Column
    private int count;

}
