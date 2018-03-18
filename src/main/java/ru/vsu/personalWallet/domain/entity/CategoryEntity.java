package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Table(name = "category")
public class CategoryEntity {
    private long id;
    private UserEntity user;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }


    @Column(name = "name")
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
