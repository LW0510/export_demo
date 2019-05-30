package com.lw.export_demo.mapper;

import com.lw.export_demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findUser(int id);
}
