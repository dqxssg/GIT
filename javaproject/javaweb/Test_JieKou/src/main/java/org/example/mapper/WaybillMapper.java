package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Customer;
import org.example.pojo.Waybill;

import java.util.List;

public interface WaybillMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  waybill")
    @ResultMap("ResultMap")
    List<Waybill> selectAllWaybill();

    /**
     * 插入数据
     *
     * @param waybill
     */
    @Insert("insert into waybill values ( null,#{username},#{password},#{trackingnumber}," +
            "#{productname},#{vehicletype},#{carnumber},#{deliverydate},#{shipper},#{consignee}," +
            "#{deliverystation},#{recrivingstation},#{weight},#{railwayfreight}," +
            "#{automobilefreight},#{servicecharge},#{shippingfreight},#{storagecharge}," +
            "#{cleaningcharge},#{discountamount},#{state})")
    void waybillAdd(Waybill waybill);

    /**
     * 修改
     *
     * @param waybill
     */
    @Update("update waybill set Username=#{null},Password=#{null}," +
            "TrackingNumber=#{trackingnumber},ProductName=#{productname}," +
            "VehicleType=#{vehicletype},CarNumber=#{carnumber},DeliveryDate=#{deliverydate}," +
            "Shipper=#{shipper}," +
            "Consignee=#{consignee},DeliveryStation=#{deliverystation}," +
            "ReceivingStation=#{receivingstation},weight=#{weight}," +
            "RailwayFreight=#{railwayfreight},AutomobileFreight=#{automobilefreight}," +
            "ServiceCharge=#{servicecharge}," +
            "StorageCharge=#{storagecharge}," +
            "CleaningCharge=#{cleaningcharge}," +
            "DiscountAmount=#{discountamount}," +
            "state=#{state}where id =#{id}")
    void waybillupdate(Waybill waybill);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from waybill where id = #{id}")
    void waybilldeleteById(int id);
}
