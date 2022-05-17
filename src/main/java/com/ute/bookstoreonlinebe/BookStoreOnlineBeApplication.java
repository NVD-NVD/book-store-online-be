package com.ute.bookstoreonlinebe;

import com.ute.bookstoreonlinebe.dtos.user.UserDto;
import com.ute.bookstoreonlinebe.repositories.UserRepository;
import com.ute.bookstoreonlinebe.services.UserService;
import com.ute.bookstoreonlinebe.utils.EnumGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;

@SpringBootApplication
public class BookStoreOnlineBeApplication implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Value("${default.password}")
    private String defaultPassword;
    @Value("${default.password}")
    private String defaultAvatar;

    public static void main(String[] args) {
        SpringApplication.run(BookStoreOnlineBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            UserDto userDto = new UserDto("admin@gmail.com",defaultPassword,
                    "admin", "book store",
                    new SimpleDateFormat("MM/dd/yyyy").parse(("4/20/1997")),
                            EnumGender.Male, "thu duc, tp.HCM",
                    "0989542812", defaultAvatar);
            userService.createAdmin(userDto);
        }
    }
}
