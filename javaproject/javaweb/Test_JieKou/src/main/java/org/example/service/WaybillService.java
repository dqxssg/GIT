package org.example.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.mapper.CustomerMapper;
import org.example.mapper.WaybillMapper;
import org.example.pojo.Customer;
import org.example.pojo.Waybill;
import org.example.util.SqlSessionFactoryUtils;

import java.util.List;

public class WaybillService {
    //调用BrandMapper.selectAll()
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Waybill> selectAllWaybill() {
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        WaybillMapper mapper = sqlSession.getMapper(WaybillMapper.class);
        //调用方法
        List<Waybill> waybills = mapper.selectAllWaybill();
        //释放资源
        sqlSession.close();
        return waybills;
    }


    /**
     * 添加
     *
     * @param waybill
     */
    public void waybillAdd(Waybill waybill) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WaybillMapper mapper = sqlSession.getMapper(WaybillMapper.class);
        mapper.waybillAdd(waybill);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 修改
     *
     * @param waybill
     */
    public void waybillUpdate(Waybill waybill) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WaybillMapper mapper = sqlSession.getMapper(WaybillMapper.class);
        mapper.waybillupdate(waybill);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除
     *
     * @param id
     */
    public void waybillDeleteById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WaybillMapper mapper = sqlSession.getMapper(WaybillMapper.class);
        mapper.waybilldeleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
