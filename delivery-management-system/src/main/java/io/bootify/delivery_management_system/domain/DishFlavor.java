package io.bootify.delivery_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name="dish_flavor")
public class DishFlavor {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private Long dishId;

    @Column(length = 64)
    private String name;

    //flavor data list
    @Column(length = 700)
    private String value;

    @Transient
    private Integer isDeleted;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="dish_id",nullable = false)
    private Dish dish;
}
