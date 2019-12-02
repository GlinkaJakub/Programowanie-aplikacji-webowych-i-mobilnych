package com.glinka.p2.dao;

import com.glinka.p2.entity.User2;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Lob;
import java.util.List;

public interface UserRepository extends JpaRepository<User2, Integer> {

    public List<User2> findAll();
    public User2 findUserByLogin(String login);
    public User2 findUserById(Long id);
    public void deleteByLogin(String login);
    public void deleteById(Long id);
}
