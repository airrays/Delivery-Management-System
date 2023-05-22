package io.bootify.delivery_management_system.domain;

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
public class Category {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer type;

    @Column(nullable = false, unique = true, length = 64)
    private String name;

    @Column(nullable = false)
    private Integer sort;

    @Column
    private Integer status;

    @Column
    private OffsetDateTime createTime;

    @Column
    private OffsetDateTime updateTime;

    @Transient
    @Column
    private Integer isDeleted;

    @Column
    private Long createUser;

    @Column
    private Long updateUser;


}
