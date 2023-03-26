package com.example.springboot_boot_jiekou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot_boot_jiekou.bean.Grade;

import java.util.List;

public interface GradeService extends IService<Grade> {
    /**
     * 查询所有
     */
    List<Grade> SeleteGrade();

    /**
     * 添加
     */
    int InsertGrade(Grade grade);

    /**
     * 修改
     */
    int UpdateGrade(Grade grade);

    /**
     * 删除
     */
    int DeleteGrade(int id);
}
