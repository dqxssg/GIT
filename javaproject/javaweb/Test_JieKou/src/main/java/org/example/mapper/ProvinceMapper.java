package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Customer;
import org.example.pojo.Province;

import java.util.List;

public interface ProvinceMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  province")
    @ResultMap("ResultMap")
    List<Province> selectAllProvince();

    /**
     * 插入数据
     *
     * @param province
     */
    @Insert("insert into province values (null,#{province})")
    void provinceAdd(Province province);


    /**
     * 修改
     *
     * @param province
     */
    @Update("update province set province=#{province}where id =#{id}")
    void provinceupdate(Province province);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from province where id = #{id}")
    void provincedeleteById(int id);
}
