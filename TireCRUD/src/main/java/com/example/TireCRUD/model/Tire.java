package com.example.TireCRUD.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Tire")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Tire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String Type;

    @Column
    private String Name;

}
