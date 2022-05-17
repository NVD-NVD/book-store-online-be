package com.ute.bookstoreonlinebe.models;

import com.mongodb.lang.Nullable;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Value
public class Order {
    @Field("by")
    private String customer;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @Nullable
    @DBRef
    private List<Book> books;

    private Boolean enable;
}
