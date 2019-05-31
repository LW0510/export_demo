package com.lw.export_demo.mapper;

import com.lw.export_demo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportMapper {

    User findUser(int id);

    List<User> findAllUser();
}
