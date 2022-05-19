package com.ute.bookstoreonlinebe.services.user;

import com.ute.bookstoreonlinebe.dtos.PasswordDto;
import com.ute.bookstoreonlinebe.dtos.user.UserDto;
import com.ute.bookstoreonlinebe.models.User;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface UserService{

//    Page<User> getUserPaging(String search, int page, int size, String sort, String column);

        User getUser(Principal principal);

        Page<User> getUserPaging(String search, int page, int size, String sort, String column);

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
