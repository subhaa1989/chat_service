package com.jobrad.chat.repository;

import com.jobrad.chat.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findByChatId(String senderId, String receiverId);
}
