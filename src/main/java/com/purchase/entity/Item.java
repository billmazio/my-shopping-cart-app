package com.purchase.entity;

import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "price",nullable = false)
    private BigDecimal price;

    @Column(name ="code",nullable = false)
    private String code;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    private List<Order> orders;

}
