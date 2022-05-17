package com.ute.bookstoreonlinebe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 04/20/2022
 * Time: 7:55 PM
 * Filename: AccountDto
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto {
    private String oldPass;
    private String newPass;
}
