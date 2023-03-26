package com.example.boot05webadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot05webadmin.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 继承BaseMapper之后可以省略简单的mysql功能语句
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
