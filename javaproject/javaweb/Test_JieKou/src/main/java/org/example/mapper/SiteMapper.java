package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Customer;
import org.example.pojo.Site;

import java.util.List;

public interface SiteMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from  site")
    @ResultMap("ResultMap")
    List<Site> selectAllSite();

    /**
     * 插入数据
     *
     * @param site
     */
    @Insert("insert into site values (null,#{sitename})")
    void siteAdd(Site site);

    /**
     * 修改
     *
     * @param site
     */
    @Update("update site set Name=#{sitename}where id =#{id}")
    void siteupdate(Site site);

    /**
     * 删除
     *
     * @param id
     */
    @Delete("delete from site where id = #{id}")
    void sitedeleteById(int id);
}
