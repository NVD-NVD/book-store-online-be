package com.ute.bookstoreonlinebe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ute.bookstoreonlinebe.utils.EnumGender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String email;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    private EnumGender gender;

    private String address;

    private String phone;

    private String avatar;

    private List<String> roles = new ArrayList<>();

    private boolean enable = true;
}