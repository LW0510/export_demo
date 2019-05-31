package com.lw.export_demo.service;

import com.lw.export_demo.entity.User;
import com.lw.export_demo.mapper.ExportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    @Autowired
    private ExportMapper exportMapper;

    public void export(){
        User user = exportMapper.findUser(1);

        System.out.println("user:"+ user);


    }
}
