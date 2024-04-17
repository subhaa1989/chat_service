package com.jobrad.chat.service;

import com.jobrad.chat.model.ChatRoom;
import com.jobrad.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service which handles chats between different users using unique id
 * eg: subha_server, server_subha, melek_subha etc
 */
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    public Optional<String> getChatRoomUniqueId(final String senderId, final String receiverId,
                                                final boolean createNewChatRoomIfNotExists){
        return chatRoomRepository.findByChatId(senderId, receiverId)
                .map(ChatRoom::getChatId)
                .or(
                ()->{
                    if(createNewChatRoomIfNotExists){
                      var chatRoomId=  createChatRoomId(senderId, receiverId);
                      return Optional.of(chatRoomId);
                    }
                    return Optional.empty();
                }
        );
    }

    private String createChatRoomId(String senderId, String receiverId) {
        var chatRoomId = String.format("%s_%s", senderId, receiverId);

        var senderReceiverChat = ChatRoom.builder().chatId(chatRoomId).senderId(senderId)
                .receiverId(receiverId).build();

        // We want to keep track of bidirectional messages separately to handle different message brokers
        var receiverSenderChat = ChatRoom.builder().chatId(chatRoomId).senderId(receiverId)
                .receiverId(senderId).build();

        chatRoomRepository.save(senderReceiverChat);
        chatRoomRepository.save(receiverSenderChat);

        return chatRoomId;

    }
}
