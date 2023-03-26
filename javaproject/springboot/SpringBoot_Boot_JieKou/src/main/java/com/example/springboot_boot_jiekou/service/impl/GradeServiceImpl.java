package com.example.springboot_boot_jiekou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot_boot_jiekou.bean.Grade;
import com.example.springboot_boot_jiekou.mapper.GradeMapper;
import com.example.springboot_boot_jiekou.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper,Grade> implements GradeService {
    @Autowired
    GradeMapper gradeMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Grade> SeleteGrade() {
        return gradeMapper.SelectGrade();
    }

    /**
     * 插入
     */
    @Override
    public int InsertGrade(Grade grade) {
        return gradeMapper.InsertGrade(grade);
    }

    /**
     * 修改
     */
    @Override
    public int UpdateGrade(Grade grade) {
        return gradeMapper.UpdateGrade(grade);
    }

    /**
     * 删除
     */
    @Override
    public int DeleteGrade(int id) {
        return gradeMapper.DeleteGrade(id);
    }
}
