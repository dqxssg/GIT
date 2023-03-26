package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Reservation;
import org.example.pojo.User;

import java.util.List;

public interface ReservationMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  reservation")
    @ResultMap("ResultMap")
    List<Reservation> selectAll();

    /**
     * 插入数据
     *
     * @param reservation
     */
    @Insert("insert into reservation values (null,#{time},#{productname},#{shipper},#{consignee},#{weight})")
    void Add(Reservation reservation);

    /**
     * 修改
     *
     * @param reservation
     */
    @Update("update reservation set Time=#{time},productname=#{productname},shipper=#{shipper},consignee=#{consignee},weight=#{weight}where id =#{id}")
    void update(Reservation reservation);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from reservation where id = #{id}")
    void deleteById(int id);
}