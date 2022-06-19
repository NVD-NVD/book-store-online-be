package com.ute.bookstoreonlinebe.config;

import com.ute.bookstoreonlinebe.dtos.user.UserDto;
import com.ute.bookstoreonlinebe.entities.Book;
import com.ute.bookstoreonlinebe.entities.Order;
import com.ute.bookstoreonlinebe.entities.User;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedCardListBook;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedCart;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedPrice;
import com.ute.bookstoreonlinebe.exceptions.InvalidException;
import com.ute.bookstoreonlinebe.repositories.BookRepository;
import com.ute.bookstoreonlinebe.repositories.CategoryRepository;
import com.ute.bookstoreonlinebe.repositories.OrderRepository;
import com.ute.bookstoreonlinebe.repositories.UserRepository;
import com.ute.bookstoreonlinebe.services.category.CategoryService;
import com.ute.bookstoreonlinebe.services.user.UserService;
import com.ute.bookstoreonlinebe.utils.enums.EnumCurrency;
import com.ute.bookstoreonlinebe.utils.enums.EnumGender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Profile("dev")
public class DataSeedingListerner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Value("${default.password}")
    private String defaultPassword;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            UserDto userDto = new UserDto("zerodev247@gmail.com", defaultPassword,
                    "admin",
                    new SimpleDateFormat("MM/dd/yyyy").parse(("4/20/1997")),
                    EnumGender.Male, "thu duc, tp.HCM",
                    "0989542812");
            userService.createAdmin(userDto);

        }
//        try {
//            User user = userService.getUserByID("62a05b38cad9e53cc7bc6b8d");
//            Order order = new Order();
//            order.setUser(user);
//            order.setBooks(user.getCarts().getListBookInCart());
//            order.setAddress(user.getAddress());
//            order.setPhone(user.getPhone());
//            order.setOrderDate(new Date());
//            order.setNote("Giao hang tiet kiem");
//            float tt = 0.0F;
//            for (EmbeddedCardListBook e : user.getCarts().getListBookInCart()){
//                tt += e.getTotal();
//            }
//            order.setSubtotal(new EmbeddedPrice( tt, EnumCurrency.vnd));
//            orderRepository.save(order);
//
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        try{
//            List<User> users = userRepository.findAll();
//            List<Book> books = bookRepository.findAll();
//            users.forEach(user -> {
//                EmbeddedCart embeddedCart = new EmbeddedCart();
//                List<EmbeddedCardListBook> list = new ArrayList<>();
//                EmbeddedCardListBook embeddedCardListBook = new EmbeddedCardListBook();
//                Random random = new Random();
//                for (int i = 0; i<5; i++) {
//                    int randomIndex = random.nextInt(books.size());
//                    Book book = books.get(randomIndex);
//                    embeddedCardListBook.setBook(book);
//                    embeddedCardListBook.setQuantity(randomIndex);
//                    embeddedCardListBook.setTotal(book.getPrice().getPrice()*randomIndex);
//                    list.add(embeddedCardListBook);
//                }
//                embeddedCart.setListBookInCart(list);
//                user.setCarts(embeddedCart);
//                userRepository.save(user);
//            });
//        }catch (Exception e){
//            System.out.println(e);
//        }

    }
}
