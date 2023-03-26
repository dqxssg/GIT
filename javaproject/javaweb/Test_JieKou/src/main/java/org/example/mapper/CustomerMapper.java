package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Customer;

import java.util.List;

public interface CustomerMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  customer")
    @ResultMap("ResultMap")
    List<Customer> selectAllCustomer();

    /**
     * 插入数据
     *
     * @param customer
     */
    @Insert("insert into customer values (null,#{name},#{gender},#{telephone},#{workunit},#{idnumber},#{facsimile},#{address},#{remarks})")
    void customerAdd(Customer customer);

    /**
     * 修改
     *
     * @param customer
     */
    @Update("update customer set Name=#{name},gender=#{gender},Telephone=#{telephone},WorkUnit=#{workunit},IdNumber=#{idnumber},Facsimile=#{facsimile},Address=#{address},Remarks=#{remarks}where id =#{id}")
    void customerupdate(Customer customer);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from customer where id = #{id}")
    void customerdeleteById(int id);
}
