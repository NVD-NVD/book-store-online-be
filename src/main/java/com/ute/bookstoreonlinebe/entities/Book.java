package com.ute.bookstoreonlinebe.entities;

import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedDescription;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedCategory;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedPrice;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedPublishers;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Document(collection = "books")
@TypeAlias("Product")
public class Book {
    @Id
    private String id;

    private String name;

    private String author;

    private List<EmbeddedPublishers> publishers = new ArrayList<>();

    private List<EmbeddedDescription> description = new ArrayList<>();;

    private EmbeddedPrice price;

    private List<String> image_URLs = new ArrayList<>();

    private long quantity;

    private int discount;

    private List<EmbeddedCategory> fallIntoCategories = new ArrayList<>();

    private boolean enable = true;
}
