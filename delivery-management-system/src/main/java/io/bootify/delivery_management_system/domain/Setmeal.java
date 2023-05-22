package io.bootify.delivery_management_system.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
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
public class Setmeal {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false, unique = true, length = 64)
    private String name;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    //0:stop 1:active
    @Column
    private Integer status;

    @Column(name = "\"description\"", length = 512)
    private String description;

    @Column
    private String code;
    @Column
    private String image;

    @Column
    private OffsetDateTime createTime;

    @Column
    private OffsetDateTime updateTime;

    @Column
    private Long createUser;

    @Column
    private Long updateUser;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @Transient
    @Column
    private Integer isDeleted;

}
