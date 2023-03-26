package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    //查询所有
    List<Brand> selectAll();

    //查看详情：根据Id进行查找
    Brand selectById(int id);

    /**
     * 条件查询
     * 接收参数：
     * 1、散装参数：如果方法中有多个参数，需要使用@Param("SQL参数占位符名称")
     * 2、对象参数：
     * 3、Map集合参数：
     */
    List<Brand> selectByCondition(@Param("status") int status, @Param("brandName") String brandName, @Param("companyName") String companyName);

    List<Brand> selectByCondition(Brand brand);

    List<Brand> selectByCondition(Map map);

    //动态条件查询
    List<Brand> selectByConditionDT(Map map);

    //单条件动态查询
    List<Brand>selectByConditionSingle(Brand brand);

    //添加
    void add(Brand brand);

    //修改
    int update(Brand brand);

    //动态修改
    int updateDT(Brand brand);

    //删除
    void deleteById(int id);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);
}
