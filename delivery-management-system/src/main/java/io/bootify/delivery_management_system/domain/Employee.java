package io.bootify.delivery_management_system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.hibernate.annotations.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "primary_sequence")
    private Long id;

    @Column(name="name",nullable = false, length = 32)
    private String name;

    @Column(nullable = false, name = "username",unique = true, length = 32)
    private String username;

    @Column(nullable = false, length = 64)
    @JsonIgnore
    private String password;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 2)
    private String sex;

    @Column(nullable = false, length = 18)
    private String idNumber;

    @Column(nullable = false, columnDefinition = "integer default 1")
    private Integer status=1;

//    @Column
//    private OffsetDateTime createTime;
//
//    @Column
//    private OffsetDateTime updateTime;
    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    @Column
    private Long createUser;

    @Column
    private Long updateUser;

    @Version
    private Long version;

//    public Employee(String name, String username, String password, String phone, String sex, String idNumber, Integer status, LocalDateTime createTime, LocalDateTime updateTime, Long createUser, Long updateUser) {
//        this.name = name;
//        this.username = username;
//        this.password = password;
//        this.phone = phone;
//        this.sex = sex;
//        this.idNumber = idNumber;
//        this.status = status;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//        this.createUser = createUser;
//        this.updateUser = updateUser;
//    }

//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    private OffsetDateTime dateCreated;
//
//    @LastModifiedDate
//    @Column(nullable = false)
//    private OffsetDateTime lastUpdated;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                '}';
    }
}
