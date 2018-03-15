package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Table(name = "category")
public class CategoryEntity {
    private String id;
    private String userId;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "user_id", nullable = false)
    public String getUserId() {
        return userId;
    }


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public CategoryEntity setId(String id) {
        this.id = id;
        return this;
    }

    public CategoryEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public CategoryEntity setName(String name) {
        this.name = name;
        return this;
    }
}
