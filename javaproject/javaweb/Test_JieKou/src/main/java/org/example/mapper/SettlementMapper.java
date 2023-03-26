package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Customer;
import org.example.pojo.Settlement;

import java.util.List;

public interface SettlementMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  settlement")
    @ResultMap("ResultMap")
    List<Settlement> selectAllSettlement();

    /**
     * 插入数据
     *
     * @param settlement
     */
    @Insert("insert into settlement values (null,#{WaybillNumber},#{ProductName},#{CarNumber},#{DeliveryDate},#{Shipper},#{weight},#{price})")
    void settlementAdd(Settlement settlement);

    /**
     * 修改
     *
     * @param settlement
     */
    @Update("update settlement set WaybillNumber=#{WaybillNumber},ProductName=#{ProductName},CarNumber=#{CarNumber},DeliveryDate=#{DeliveryDate},Shipper=#{Shipper},weight=#{weight},price=#{price}where id =#{id}")
    void settlementupdate(Settlement settlement);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from settlement where id = #{id}")
    void settlementdeleteById(int id);
}
