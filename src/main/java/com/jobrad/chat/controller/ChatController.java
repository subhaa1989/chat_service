package com.jobrad.chat.controller;

import com.jobrad.chat.model.Chat;
import com.jobrad.chat.model.ChatNotification;
import com.jobrad.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    // useful to send objects to queue
    // helps to notify receiver about messages
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void createChat(
            @Payload Chat chat
    ){
        var message = chatMessageService.save(chat);
    // notify the recipient
    simpMessagingTemplate.convertAndSendToUser(
        chat.getReceiverId(),
        "/queue/messages",
        ChatNotification.builder()
            .id(message.getId())
            .senderId(message.getSenderId())
            .receiverId(message.getReceiverId())
            .content(message.getContent()).build());
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<List<Chat>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("receiverId") String receiverId)
    {
        return ResponseEntity.ok(chatMessageService.findAllChats(senderId, receiverId));

    }



}
