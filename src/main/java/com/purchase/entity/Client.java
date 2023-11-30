package com.purchase.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "surname",nullable = false)
    private String surname;

    @Column(name = "postalCode",nullable = false)
    private String postalCode;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "email",nullable = false)
    private String email;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Order> orders;
}
