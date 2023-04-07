package io.bootify.delivery_management_system.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class AddressBook {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 50)
    private String consignee;

    @Column
    private Integer sex;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(length = 12)
    private String provinceCode;

    @Column(length = 32)
    private String provinceName;

    @Column(length = 12)
    private String cityCode;

    @Column(length = 32)
    private String cityName;

    @Column(length = 12)
    private String districtCode;

    @Column(length = 32)
    private String districtName;

    @Column(length = 200)
    private String detail;

    @Column(length = 100)
    private String label;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
