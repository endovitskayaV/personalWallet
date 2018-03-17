package ru.vsu.personalWallet.domain.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public class CategoryDto {
    private long id;
    private long userId;
    private String name;

    @JsonValue
    public long getId() {
        return id;
    }

    public CategoryDto setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public CategoryDto setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }
}
