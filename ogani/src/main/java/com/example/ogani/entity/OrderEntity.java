package com.example.ogani.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 50, unique = true)
    private String firstname;

    @Column(name = "last_name", length = 50)
    private String lastname;

    @Column(name = "country")
    private String country;

    @Column(name = "address", length = 300)
    private String address;

    @Column(name = "town")
    private String town;

    @Column(name = "state")
    private String state;

    @Column(name = "post_code")
    private long postCode;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone",length = 20, unique = true)
    private String phone;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "total_price")
    private long totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "order")
    @JsonBackReference
    private Set<OrderDetailEntity> orderdetails;


}
