package com.example.springboot_boot_jiekou.service;

import com.example.springboot_boot_jiekou.bean.Users;

public interface UsersService {
    /**
     * 根据账号查找
     *
     * @param username
     * @return
     */
    Users getByUserName(String username, String password);
}
