package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Table(name = "category")
public class CategoryEntity {
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

    CategoryEntity setId(long id) {
        this.id = id;
        return this;
    }

    CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }
}
