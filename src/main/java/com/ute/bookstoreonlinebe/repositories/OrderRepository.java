package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
