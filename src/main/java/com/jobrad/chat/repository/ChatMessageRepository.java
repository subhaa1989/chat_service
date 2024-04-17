package com.jobrad.chat.repository;

import com.jobrad.chat.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ChatMessageRepository extends MongoRepository<Chat, String> {
    List<Chat> findByChatId(String id);
}
