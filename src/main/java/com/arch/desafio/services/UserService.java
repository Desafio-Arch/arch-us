package com.arch.desafio.services;

import com.arch.desafio.models.User;
import com.arch.desafio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
