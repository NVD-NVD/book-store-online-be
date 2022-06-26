package com.ute.bookstoreonlinebe.repositories;

import com.ute.bookstoreonlinebe.entities.TransactionsHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsHistoryRepository extends MongoRepository<TransactionsHistory, String> {
}
