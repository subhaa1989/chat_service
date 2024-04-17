package com.jobrad.chat.service;

import com.jobrad.chat.exception.ChatServiceException;
import com.jobrad.chat.model.Chat;
import com.jobrad.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public Chat save(final Chat chat){
    var chatId =
        chatRoomService
            .getChatRoomUniqueId(chat.getSenderId(), chat.getReceiverId(), true)
            .orElseThrow(
                () -> {
                  throw new ChatServiceException("Chat Room not found");
                });
        chat.setChatId(chatId);
        return chatMessageRepository.save(chat);
    }

  public List<Chat> findAllChats(String senderId, String receiverId) {
    // Here we only want to retrieve id, creating is not the scope of this class
    var chatId = chatRoomService.getChatRoomUniqueId(senderId, receiverId, false);
    if (chatId.isPresent()) {
      return chatMessageRepository.findByChatId(chatId.get());
    }
    return List.of();
  }
}
