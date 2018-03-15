package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Table(name = "category")
public class CategoryEntity {
    private String id;
    private UserEntity user;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
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

    public CategoryEntity setId(String id) {
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
