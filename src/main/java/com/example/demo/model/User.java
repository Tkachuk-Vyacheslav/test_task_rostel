package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data // эта аннотац автоматич подтянет геттеры , сеттеры и конструкт-р (библ lombok)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "surname")
    private String lastName;

}
