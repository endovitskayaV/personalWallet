package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode
@Table(name = "category")
public class CategoryEntity {
    private long id;
    private UserEntity user;
    private String name;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @ManyToOne
    @NotNull
    public UserEntity getUser() {
        return user;
    }


    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public CategoryEntity setId(long id) {
        this.id = id;
        return this;
    }

    public CategoryEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }
}
