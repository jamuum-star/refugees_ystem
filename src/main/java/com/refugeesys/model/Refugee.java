package com.refugeesys.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refugee_tb")
public class Refugee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private String fullname;
    private String familysize;
    @Column(unique = false,length = 20)
    private String phone;
    @Column(nullable = true)
    private String address;
    private String status;
}
