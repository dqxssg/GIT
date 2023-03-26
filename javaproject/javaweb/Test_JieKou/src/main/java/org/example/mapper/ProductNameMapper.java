package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Customer;
import org.example.pojo.ProductName;

import java.util.List;

public interface ProductNameMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  productname")
    @ResultMap("ResultMap")
    List<ProductName> selectAllProductName();

    /**
     * 插入数据
     *
     * @param productName
     */
    @Insert("insert into productname values (null,#{productname})")
    void productNameAdd(ProductName productName);

    /**
     * 修改
     *
     * @param productName
     */
    @Update("update productname set productname=#{productname}where id =#{id}")
    void productNameupdate(ProductName productName);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from productname where id = #{id}")
    void productNamedeleteById(int id);
}
