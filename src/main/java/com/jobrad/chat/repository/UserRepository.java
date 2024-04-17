package com.jobrad.chat.repository;

import com.jobrad.chat.model.Status;
import com.jobrad.chat.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Key is unique_id eg: subha_melak and record is User object
 */
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
