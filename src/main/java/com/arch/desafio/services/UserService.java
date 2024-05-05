package com.arch.desafio.services;

import com.arch.desafio.models.User;
import com.arch.desafio.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUserName(username);
    }

    public Optional<User> findByUserName(String name) {
        return this.userRepository.findByUserName(name);
    }
}
