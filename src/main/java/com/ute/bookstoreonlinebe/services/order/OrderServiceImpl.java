package com.ute.bookstoreonlinebe.services.order;

import com.ute.bookstoreonlinebe.entities.Order;
import com.ute.bookstoreonlinebe.entities.User;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedCardListBook;
import com.ute.bookstoreonlinebe.entities.embedded.EmbeddedPrice;
import com.ute.bookstoreonlinebe.exceptions.InvalidException;
import com.ute.bookstoreonlinebe.exceptions.NotFoundException;
import com.ute.bookstoreonlinebe.repositories.OrderRepository;
import com.ute.bookstoreonlinebe.services.mailsender.MailSenderService;
import com.ute.bookstoreonlinebe.services.user.UserService;
import com.ute.bookstoreonlinebe.utils.PageUtils;
import com.ute.bookstoreonlinebe.utils.enums.EnumCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;

    private UserService userService;

    private MailSenderService mailSenderService;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, MailSenderService mailSenderService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getOrderPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page,size,sort,column);
        return orderRepository.getOrderPaging(search, pageable);
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order có id %s không tồn tại", id)));
    }

    @Override
    public Order createNewOrder(String userID, Principal principal) {
        User user = userService.checkUserWithIDAndPrincipal(userID,principal);
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setBooks(user.getCarts().getListBookInCart());
        order.setSubtotal(new EmbeddedPrice(0, EnumCurrency.vnd));
        float subTotal = 0.0F;
        for(EmbeddedCardListBook book : user.getCarts().getListBookInCart()){
            subTotal += book.getTotal();
        }
        order.setSubtotal(new EmbeddedPrice(subTotal, EnumCurrency.vnd));
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setNode(null);
        order.setStatus(true);
        order.setPay(false);
        order.setShipping(false);
        order.setDelivered(false);
        orderRepository.save(order);
        mailSenderService.sendNewOrder(user, order);
        return order;
    }

    @Override
    public Order disableOrder(String userID, Principal principal) {
        return null;
    }

    @Override
    public Order callOffOrder(String userID, String orderID, Principal principal) {
        User user = userService.checkUserWithIDAndPrincipal(userID,principal);
        Order order = getOrderById(orderID);
        if (!order.getUser().getId().equals(user.getId()))
        {
            throw new InvalidException(
                    String.format("Order có id %s không thuộc về user có id %s",orderID, userID));
        }
        if (order.isStatus() && order.isShipping())
        {
            throw new InvalidException(
                    String.format("Không thể huy đơn hàng có id %s, vì đơn hàng đã được giao.", orderID));
        }
        order.setStatus(false);
        orderRepository.save(order);
        mailSenderService.sendCallOffOrder(user, order.getId());
        return order;
    }

    @Override
    public Order changeStatusOrder(String id) {
        Order order = getOrderById(id);
        order.setStatus(!order.isStatus());
        return orderRepository.save(order);
    }

    @Override
    public Order changeStatusPayOfOrder(String id) {
        return null;
    }

    @Override
    public Order changeShippingOrder(String id) {
        Order order = getOrderById(id);
        order.setShipping(!order.isShipping());
        return orderRepository.save(order);
    }

    @Override
    public Order changeDeliveredOrder(String id) {
        Order order = getOrderById(id);
        order.setDelivered(!order.isDelivered());
        return orderRepository.save(order);
    }
}
