package com.ute.bookstoreonlinebe.dtos.user;

import com.ute.bookstoreonlinebe.utils.EnumGender;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String email;
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

    public UserDto(String email, String password, String firstName,
                   String lastName, Date birthday, EnumGender gender,
                   String address, String phone, String avatar) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
    }
}
