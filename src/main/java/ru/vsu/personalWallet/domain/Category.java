package ru.vsu.personalWallet.domain;


import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
public class Category {
    private long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    Category setId(long id) {
        this.id = id;
        return this;
    }

    Category setName(String name) {
        this.name = name;
        return this;
    }
}
