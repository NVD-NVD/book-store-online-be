package com.ute.bookstoreonlinebe.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document(collection = "books")
@TypeAlias("Product")
public class Book {
    @Id
    private String id;

    private String name;

    private String description;

    private float price;

    private List<String> image_URLs = new ArrayList<>();

    private Set<EmbeddedCategory> fallIntoCategories = new HashSet<>();
}
