package com.example.rediscache.controller;


import com.example.rediscache.entity.User;
import com.example.rediscache.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping("")
    public List<User> list(){
        logger.info("list user");
        return service.listUser();
    }

    @PostMapping("")
    public void create(@RequestBody User user){
        logger.info("create user {}", user);
        service.createUser(user);
    }


    @Cacheable(value = "single", key = "#id")
    @GetMapping("/{id}")
    public User getUserByID(@PathVariable String id){
        logger.info("get user with id {}", id);
        return service.getuserByID(id);
    }

    @CachePut(value = "single", key = "#user.id")
    @PutMapping("")
    public User updateUserByID(@RequestBody User user) {
        logger.info("update user with id {}", user.getId());
        service.updateuser(user);
        return user;
    }

    @CacheEvict(value = "single", key = "#id")
    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable String id) {
        logger.info("delete user with id {}", id);
        service.deleteuser(id);
    }
}
