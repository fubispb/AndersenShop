package entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity extends AbstractIdentifiableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private UserEntity user;

    @Column
    private boolean processed;

    @Column
    private int sum;

    @Column
    private LocalDate date;



}
