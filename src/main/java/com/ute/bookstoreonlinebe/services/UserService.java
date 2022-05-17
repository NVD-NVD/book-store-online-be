package com.ute.bookstoreonlinebe.services;

import com.ute.bookstoreonlinebe.dtos.PasswordDto;
import com.ute.bookstoreonlinebe.dtos.user.UserDto;
import com.ute.bookstoreonlinebe.models.User;

import java.security.Principal;
import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : NVD-NVD
 * Date      : 04/20/2022
 * Time      : 8:38 PM
 * Filename  : UserService
 */
public interface UserService{

//    Page<User> getUserPaging(String search, int page, int size, String sort, String column);

        User getUser(Principal principal);

        User getUserCoreByEmail(String email);

        User addNewUserCore(String firstName, String lastname,String email,String password);

        User createAdmin(UserDto dto);

        User createNewUser(UserDto dto);

        User updateUser(String id,UserDto dto);

        User changeStatus(String id,Principal principal);

        User changePassword(String id, PasswordDto passwordDto);

        List<String> getRoles();

        User updateName(UserDto dto,Principal principal);

        List<User> getAllUser();
}
