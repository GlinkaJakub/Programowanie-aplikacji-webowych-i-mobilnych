package com.glinka.p2.service;

import com.glinka.p2.dao.UserRepository;
import com.glinka.p2.entity.JwtRequest;
import com.glinka.p2.entity.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User2> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User2 findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public void saveNewUser(User2 user2) {
        userRepository.save(user2);
    }

    @Override
    public void deleteByLogin(String login) {
        userRepository.deleteByLogin(login);
    }

    @Override
    public String loginUser(JwtRequest jwtRequest) {
        User2 user = findUserByLogin(jwtRequest.getUsername());

        if (user == null){
            return "Błędny login";
        } else if(user.getLogin().equals(jwtRequest.getUsername()) && user.getPassword().equals(jwtRequest.getPassword())){
            return "Zalogowany";
        } else if(user.getLogin().equals(jwtRequest.getUsername()) && !user.getPassword().equals(jwtRequest.getPassword())){
            return "Błędno hasło";
        } else {
            return "nieznany błąd";
        }
    }

    @Override
    public User2 findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
