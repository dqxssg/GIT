package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Transportvehicleinformation;
import org.example.pojo.User;

import java.util.List;

public interface TransportvehicleinformationMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  transportvehicleinformation")
    @ResultMap("ResultMap")
    List<Transportvehicleinformation> selectAll();

    /**
     * 插入数据
     *
     * @param transportvehicleinformation
     */
    @Insert("insert into transportvehicleinformation values (null,#{driver},#{mobile},#{carnumber})")
    void add(Transportvehicleinformation transportvehicleinformation);

    /**
     * 修改
     *
     * @param transportvehicleinformation
     */
    @Update("update transportvehicleinformation set driver=#{driver},mobile=#{mobile},carnumber=#{carnumber}where id =#{id}")
    void update(Transportvehicleinformation transportvehicleinformation);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from transportvehicleinformation where id = #{id}")
    void deleteById(int id);
}