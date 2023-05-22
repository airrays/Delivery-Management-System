package io.bootify.delivery_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.One;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "dish")
public class Dish {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String name;

    @Column(nullable = false)
    private Long categoryId;

    @Column(precision = 12, scale = 2)
    private BigDecimal price;

    @Column(length = 200)
    private String image;

    @Column(name = "\"description\"", length = 400)
    private String description;

    @Column
    private Integer status;

    @Transient
    @Column Integer sort;
    @Transient
    @Column Integer isDeleted;
    @Column
    private OffsetDateTime createTime;

    @Column
    private OffsetDateTime updateTime;

    @Column
    private Long createUser;

    @Column
    private Long updateUser;

    @JsonIgnore
    @OneToMany(mappedBy = "dish")
    private Set<DishFlavor> dishFlavors;

}
