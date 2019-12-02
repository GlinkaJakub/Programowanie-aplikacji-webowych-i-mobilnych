package com.glinka.p2.service;

import com.glinka.p2.entity.JwtRequest;
import com.glinka.p2.entity.User2;

import java.util.List;

public interface UserService {

    public List<User2> findAll();
    public User2 findUserByLogin(String login);
    public User2 findUserById(Long id);
    void saveNewUser(User2 user2);
    void deleteByLogin(String login);
    void deleteById(Long id);
    String loginUser(JwtRequest jwtRequest);
}
