package com.glinka.p2.dao;

import com.glinka.p2.entity.User2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtRepository extends JpaRepository<User2, Integer> {

    public User2 findUserByLogin(String login);

}
