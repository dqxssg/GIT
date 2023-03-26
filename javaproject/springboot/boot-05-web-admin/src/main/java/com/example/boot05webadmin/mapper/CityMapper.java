package com.example.boot05webadmin.mapper;

import com.example.boot05webadmin.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import javax.swing.plaf.PanelUI;

@Mapper
public interface CityMapper {
    @Select("select * from city where id=#{id}")
    public City getById(int id);

    /**
     * keyProperty表示主键
     *
     * @param city
     */
    @Insert("insert into city values (null,#{name}, #{state}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(City city);
}
