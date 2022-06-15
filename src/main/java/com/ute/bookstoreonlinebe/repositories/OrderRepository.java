package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{}")
    Page<Order> getOrderPaging(String search, Pageable pageable);
}
