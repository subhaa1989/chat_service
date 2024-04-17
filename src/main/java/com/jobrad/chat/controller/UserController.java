package com.jobrad.chat.controller;

import com.jobrad.chat.service.UserService;
import com.jobrad.chat.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @MessageMapping("/user.connectUser")
  @SendTo("/user/public")
  public User connectUser(@Payload User user) {
    userService.connectUser(user);
    return user;
  }

  @MessageMapping("/user.disconnectUser")
  @SendTo("/user/public")
  public User disconnectUser(@Payload User user) {
    userService.disconnect(user);
    return user;
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> findConnectedUsers() {
    return ResponseEntity.ok(userService.findALlConnectedUsers());
  }
}
