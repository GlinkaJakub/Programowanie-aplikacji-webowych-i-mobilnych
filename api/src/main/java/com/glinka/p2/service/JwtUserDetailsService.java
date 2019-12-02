package com.glinka.p2.service;

import java.util.ArrayList;

import com.glinka.p2.dao.JwtRepository;
import com.glinka.p2.dao.UserRepository;
import com.glinka.p2.entity.JwtRequest;
import com.glinka.p2.entity.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String login = userRepository.findUserByLogin(username).getLogin();
        String password = userRepository.findUserByLogin(username).getPassword();
        System.out.println(passwordEncoder.encode(password));

        if (login == null)
            throw new UsernameNotFoundException("User not found with username: " + username);

        if (login.equals(username)) {
            return new User(login, passwordEncoder.encode(password),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

    }





//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User2 user = userRepository.findUserByLogin(username);
//
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//
//        return new User(user.getLogin(), user.getPassword(),
//                new ArrayList<>());
//    }
//
//    public User2 save(JwtRequest user) {
//        User2 user2 = new User2();
//        user2.setLogin(user.getUsername());
//        user2.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user2);//    }
}
