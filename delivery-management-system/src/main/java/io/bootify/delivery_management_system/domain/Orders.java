package io.bootify.delivery_management_system.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Orders {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String number;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long addressBookId;

    @Column(nullable = false)
    private OffsetDateTime orderTime;

    @Column
    private OffsetDateTime checkoutTime;

    @Column(nullable = false)
    private Integer payMethod;

    @Column(nullable = false)
    private Integer payStatus;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(length = 100)
    private String comment;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String userName;

    @Column
    private String consignee;

    @Column
    private String cancelReason;

    @Column
    private String rejectionReason;

    @Column
    private OffsetDateTime cancelTime;

    @Column
    private OffsetDateTime estimatedDeliveryTime;

    @Column(nullable = false)
    private Boolean deliveryStatus;

    @Column
    private OffsetDateTime deliveryTime;

    @Column
    private Integer packAmount;

    @Column
    private Integer tablewareNumber;

    @Column(nullable = false)
    private Boolean tablewareStatus;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
