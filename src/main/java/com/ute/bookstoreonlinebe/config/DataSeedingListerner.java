package com.ute.bookstoreonlinebe.config;

import com.ute.bookstoreonlinebe.dtos.user.UserDto;
import com.ute.bookstoreonlinebe.repositories.CategoryRepository;
import com.ute.bookstoreonlinebe.repositories.UserRepository;
import com.ute.bookstoreonlinebe.services.category.CategoryService;
import com.ute.bookstoreonlinebe.services.user.UserService;
import com.ute.bookstoreonlinebe.utils.EnumGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeedingListerner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Value("${default.password}")
    private String defaultPassword;

    @Value("${default.password}")
    private String defaultAvatar;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            UserDto userDto = new UserDto("admin@gmail.com", defaultPassword,
                    "admin", "book store",
                    new SimpleDateFormat("MM/dd/yyyy").parse(("4/20/1997")),
                    EnumGender.Male, "thu duc, tp.HCM",
                    "0989542812", defaultAvatar);
            userService.createAdmin(userDto);
        }
        categoryRepository.deleteAll();
        if (categoryRepository.count() == 0) {
            categoryService.createNewCategory("new");
            List<String> listName = new ArrayList<>(Arrays.asList("Sách Tiếng Việt",
                    "Sách Tiếng Anh", "Sách Giáo Khoa", "Thiếu Nhi", "Giáo Khoa - Tham Khảo",
                    "Văn Học", "Toán Học", "Hóa Học", "Vật Lý", "Lịch Sử", "Địa Lý",
                    "Tiểu Thuyết", "Kinh Tế", "Khoa Học Kỹ Thuật", "Âm Nhạc"));
            listName.forEach((e) -> categoryService.createNewCategory(e));
        }
    }

}
