package com.jobrad.chat.model;

import jakarta.websocket.server.ServerEndpoint;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Document
public class Chat {

    @Id
    private String id;
    // we will receive this form front end
    private String chatId;
    private String senderId;
    private String receiverId;
    private String content;
    private Date timestamp;


}


