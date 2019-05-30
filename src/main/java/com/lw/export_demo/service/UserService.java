package com.lw.export_demo.service;

import com.lw.export_demo.entity.User;
import com.lw.export_demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findUser(int id){
        return userMapper.findUser(id);
    }
}
