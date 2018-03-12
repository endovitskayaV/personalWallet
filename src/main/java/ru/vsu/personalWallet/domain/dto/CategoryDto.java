package ru.vsu.personalWallet.domain.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public class CategoryDto {
    private String id;
    private String name;

    @JsonValue
    public String getId() {
        return id;
    }

    public CategoryDto setId(String id) {
        this.id = id;
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
