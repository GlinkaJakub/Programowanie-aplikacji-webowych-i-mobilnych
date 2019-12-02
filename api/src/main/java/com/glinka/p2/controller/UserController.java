package com.glinka.p2.controller;

import com.glinka.p2.entity.JwtRequest;
import com.glinka.p2.entity.User2;
import com.glinka.p2.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin("http://localhost:63343")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User2> findAll() {
        return userService.findAll();
    }

    @GetMapping("/logLogin/{login}")
    public boolean findUserByLogin(@PathVariable String login) {
//    public User2 findUserByLogin(@PathVariable String login) {
        User2 user = userService.findUserByLogin(login);
        if (user != null)
            return true;
        else
            return false;
//        return userService.findUserByLogin(login);
    }

    @GetMapping("/isLog")
    public String isLogin(){
        return "logged";
    }

    @PostMapping("/logId/{id}")
    public User2 findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @CrossOrigin("http://localhost:63342")
    @PostMapping("/save")
    public User2 saveNewUser(@RequestBody User2 user) {
        userService.saveNewUser(user);
        return user;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody JwtRequest jwtRequest) {
        return userService.loginUser(jwtRequest);
    }

    @Transactional
    @DeleteMapping("/deleteByLogin/{login}")
    public String deleteUserByLogin(@PathVariable String login) {
        User2 user = userService.findUserByLogin(login);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        userService.deleteByLogin(login);

        return "Delete user: '" + login;
    }

    @Transactional
    @DeleteMapping("/deleteById/{id}")
    public String deleteUserById(@PathVariable Long id) {
        User2 user = userService.findUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found!");
        }
        userService.deleteById(id);

        return "Delete user: '" + user.getLogin();
    }
}
