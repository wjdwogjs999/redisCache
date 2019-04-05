package com.example.rediscache.service;

import com.example.rediscache.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class UserService {

    private List<User> users;

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        users.add(new User("admin", "admin"));
        users.add(new User("bodoru", "engineer"));
        users.add(new User("user1", "user"));
        users.add(new User("user2", "user"));

    }

    public void createUser(User user) {
        users.add(user);
    }

    public void updateuser(User user) {
        users = users.stream().filter(p -> p.getId() != user.getId()).collect(Collectors.toList());
        users.add(user);
    }

    public void deleteuser(String userID) {
        users = users.stream().filter(p -> !p.getId().equals(userID)).collect(Collectors.toList());
    }


    /**
     * Load user content from DB *Long running method
     */
    public User getuserByID(String userID)  {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return users.stream()
                .filter(p -> p.getId().equals(userID))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot find user with id:" + userID));
    }

    public List<User> listUser(){
        return users;
    }


}
