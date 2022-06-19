package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.entities.Order;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query(value = "{}")
    Page<Order> getOrderPaging(String search, Pageable pageable);

//    @Query(value = "{$gte:ISODate("2020-03-01"),$lt:ISODate("2021-03-31")}")
//    List<Order> getOrOrderByOrderDate();
}
