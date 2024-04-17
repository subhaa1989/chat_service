package com.jobrad.chat.service;

import com.jobrad.chat.repository.UserRepository;
import com.jobrad.chat.model.Status;
import com.jobrad.chat.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public void connectUser(User user) {
        user.setStatus(Status.ONLINE);
        repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getNickName()).orElse(null);
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            repository.save(storedUser);
        }
    }

    public List<User> findALlConnectedUsers() {
       return repository.findAllByStatus(Status.ONLINE);
    }
}
