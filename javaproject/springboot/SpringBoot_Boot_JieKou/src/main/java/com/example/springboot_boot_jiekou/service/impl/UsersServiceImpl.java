package com.example.springboot_boot_jiekou.service.impl;

import com.example.springboot_boot_jiekou.bean.Users;
import com.example.springboot_boot_jiekou.mapper.UsersMapper;
import com.example.springboot_boot_jiekou.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users getByUserName(String username,String password) {
        return usersMapper.getByUserName(username,password);
    }
}
