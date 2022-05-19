package com.ute.bookstoreonlinebe.services.user;

import com.ute.bookstoreonlinebe.dtos.PasswordDto;
import com.ute.bookstoreonlinebe.dtos.user.UserDto;
import com.ute.bookstoreonlinebe.models.User;
import com.ute.bookstoreonlinebe.exceptions.InvalidException;
import com.ute.bookstoreonlinebe.exceptions.NotFoundException;
import com.ute.bookstoreonlinebe.repositories.UserRepository;
import com.ute.bookstoreonlinebe.utils.EnumRole;
import com.ute.bookstoreonlinebe.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Value("${default.password}")
    private String defaultPassword;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException(String.format("Account have email %s does not exist", principal.getName())));
    }

    @Override
    public Page<User> getUserPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return userRepository.getUserPaging(search, pageable);
    }

    @Override
    public User getUserCoreByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User addNewUserCore(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRoles(Collections.singletonList(EnumRole.ROLE_MEMBER.name()));
        user.setEnable(true);
        userRepository.save(user);
        return user;
    }

    @Override
    public User createAdmin(UserDto dto) {
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email is not empty");
        }
        User userCoreByEmail = getUserCoreByEmail(dto.getEmail());
        if (!ObjectUtils.isEmpty(userCoreByEmail)) {
            throw new InvalidException(String.format("Account has an email %s used", dto.getEmail()));
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        if (!ObjectUtils.isEmpty(dto.getPassword())) {
            user.setPassword(dto.getPassword());
        } else {
            user.setPassword(defaultPassword);
        }
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAddress(dto.getAddress());
        user.setBirthday(dto.getBirthday());
        user.setPhone(dto.getPhone());
        user.setGender(dto.getGender());
        user.setAvatar(dto.getAvatar());
        user.setEnable(true);
        user.setRoles(Arrays.asList(EnumRole.ROLE_ADMIN.name(), EnumRole.ROLE_MEMBER.name()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User createNewUser(UserDto dto) {
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email is not empty");
        }
        User userCoreByEmail = getUserCoreByEmail(dto.getEmail());
        if (!ObjectUtils.isEmpty(userCoreByEmail)) {
            throw new InvalidException(String.format("Account have email %s does not exist", dto.getEmail()));
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        if (!ObjectUtils.isEmpty(dto.getPassword())) {
            user.setPassword(dto.getPassword());
        } else {
            user.setPassword(defaultPassword);
        }
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEnable(true);
        user.setRoles(Collections.singletonList(EnumRole.ROLE_MEMBER.name()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(String id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Account have id %s does not exist", id)));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        if (!dto.getEmail().toLowerCase().trim().equals(user.getEmail()) &&
                getUserCoreByEmail(dto.getEmail().toLowerCase().trim()) == null) {
            user.setEmail(dto.getEmail().toLowerCase().trim());
        }
        user.setRoles(dto.getRoles());
        userRepository.save(user);
        return user;
    }

    @Override
    public User changeStatus(String id, Principal principal) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Account have id %s does not exist", id)));
        if (user.getEmail().equals(principal.getName())) {
            throw new InvalidException("Cannot change status for main account");
        }
        user.setEnable(!user.isEnable());
        userRepository.save(user);
        return user;
    }

    @Override
    public User changePassword(String id, PasswordDto passwordDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Account have id %s does not exist", id)));
        if (!user.getPassword().equals(passwordDto.getOldPass())){
            throw new InvalidException("Old pass does not correct");
        }
        user.setPassword(passwordDto.getNewPass());
        userRepository.save(user);
        return user;
    }

    @Override
    public List<String> getRoles() {
        return Arrays.stream(EnumRole.values()).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public User updateName(UserDto dto, Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new NotFoundException(String.format("Account have email %s does not exist", principal.getName())));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
