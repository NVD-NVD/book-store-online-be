package com.ute.bookstoreonlinebe.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "categories")
@TypeAlias(value = "Category")
public class Category implements Serializable
{
    @Id
    private String id;

    @NonNull private String name;

    @DBRef(lazy = true)
    private List<Book> productsOfCategory = new ArrayList<>();
}
