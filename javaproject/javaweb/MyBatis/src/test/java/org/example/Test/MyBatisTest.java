package org.example.Test;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.BrandMapper;
import org.example.mapper.UesrMapper;
import org.example.pojo.Brand;
import org.example.pojo.User;
import org.junit.Test;


public class MyBatisTest {
    //查询所有
    @Test
    public void testSelectAllBrand() throws IOException {
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        List<Brand> brands = mapper.selectAll();
        for (int i = 0; i < brands.size(); i++) {
            System.out.println(brands.get(i).getId());
            System.out.println(brands.get(i).getBrandName());
            System.out.println(brands.get(i).getCompanyName());
            System.out.println(brands.get(i).getOrdered());
            System.out.println(brands.get(i).getDescription());
            System.out.println(brands.get(i).getStatus());
            System.out.println(brands);
        }
        //释放资源
        sqlSession.close();
    }

    //查看详情：根据Id进行查找
    @Test
    public void testSelectByIdBrand() throws IOException {
        //模拟接收数据
        int id = 1;
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        Brand brand = mapper.selectById(id);
        System.out.println(brand.getId());
        System.out.println(brand.getBrandName());
        System.out.println(brand.getCompanyName());
        System.out.println(brand.getOrdered());
        System.out.println(brand.getDescription());
        System.out.println(brand.getStatus());
        System.out.println(brand);
        //释放资源
        sqlSession.close();
    }

    //条件查询
    @Test
    public void testSelectByIdCondition() throws IOException {
//方法：List<Brand> selectByCondition(@Param("status") int status, @Param("brandName") String brandName, @Param("companyName") String companyName);
        //模拟接收数据
        int status = 0;
        String brandName = "三只";
        String companyName = "三只";
        //处理获得的参数
        brandName = "%" + brandName + "%";
        companyName = "%" + companyName + "%";
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        List<Brand> brands = mapper.selectByCondition(status, brandName, companyName);
        for (int i = 0; i < brands.size(); i++) {
            System.out.println(brands.get(i).getId());
            System.out.println(brands.get(i).getBrandName());
            System.out.println(brands.get(i).getCompanyName());
            System.out.println(brands.get(i).getOrdered());
            System.out.println(brands.get(i).getDescription());
            System.out.println(brands.get(i).getStatus());
            System.out.println(brands);
        }
        //释放资源
        sqlSession.close();
//方法：List<Brand> selectByCondition(Brand brand);
        //模拟接收数据
        int status1 = 1;
        String brandName1 = "华为";
        String companyName1 = "华为";
        //处理获得的参数
        brandName1 = "%" + brandName1 + "%";
        companyName1 = "%" + companyName1 + "%";
        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status1);
        brand.setBrandName(brandName1);
        brand.setCompanyName(companyName1);
        //获取SqlSessionFactory
        String resource1 = "mybatis-config.xml";
        InputStream inputStream1 = Resources.getResourceAsStream(resource1);
        SqlSessionFactory sqlSessionFactory1 = new SqlSessionFactoryBuilder().build(inputStream1);
        //获取SqlSession对象
        SqlSession sqlSession1 = sqlSessionFactory1.openSession();
        //获取BrandMapper
        BrandMapper mapper1 = sqlSession1.getMapper(BrandMapper.class);
        //执行方法
        List<Brand> brands1 = mapper1.selectByCondition(brand);
        for (int i = 0; i < brands1.size(); i++) {
            System.out.println(brands1.get(i).getId());
            System.out.println(brands1.get(i).getBrandName());
            System.out.println(brands1.get(i).getCompanyName());
            System.out.println(brands1.get(i).getOrdered());
            System.out.println(brands1.get(i).getDescription());
            System.out.println(brands1.get(i).getStatus());
            System.out.println(brands1);
        }
        //释放资源
        sqlSession.close();
//方法：List<Brand> selectByCondition(Map map);
        //模拟接收数据
        int status2 = 1;
        String brandName2 = "小米";
        String companyName2 = "小米";
        //处理获得的参数
        brandName2 = "%" + brandName2 + "%";
        companyName2 = "%" + companyName2 + "%";
        //封装对象
        Map map = new HashMap();
        map.put("status", status2);
        map.put("brandName", brandName2);
        map.put("companyName", companyName2);
        //获取SqlSessionFactory
        String resource2 = "mybatis-config.xml";
        InputStream inputStream2 = Resources.getResourceAsStream(resource2);
        SqlSessionFactory sqlSessionFactory2 = new SqlSessionFactoryBuilder().build(inputStream2);
        //获取SqlSession对象
        SqlSession sqlSession2 = sqlSessionFactory2.openSession();
        //获取BrandMapper
        BrandMapper mapper2 = sqlSession2.getMapper(BrandMapper.class);
        //执行方法
        List<Brand> brands2 = mapper2.selectByCondition(map);
        for (int i = 0; i < brands2.size(); i++) {
            System.out.println(brands2.get(i).getId());
            System.out.println(brands2.get(i).getBrandName());
            System.out.println(brands2.get(i).getCompanyName());
            System.out.println(brands2.get(i).getOrdered());
            System.out.println(brands2.get(i).getDescription());
            System.out.println(brands2.get(i).getStatus());
            System.out.println(brands2);
        }
        //释放资源
        sqlSession.close();
    }

    //动态条件查询
    @Test
    public void testSelectByIdConditionDT() throws IOException {
        //模拟接收数据
        int status = 1;
        String brandName = "小米";
        String companyName = "小米";
        //处理获得的参数
        brandName = "%" + brandName + "%";
        companyName = "%" + companyName + "%";
        //封装对象
        Map map = new HashMap();
        map.put("status", status);
        map.put("brandName", brandName);
        map.put("companyName", companyName);
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        List<Brand> brands = mapper.selectByConditionDT(map);
        for (int i = 0; i < brands.size(); i++) {
            System.out.println(brands.get(i).getId());
            System.out.println(brands.get(i).getBrandName());
            System.out.println(brands.get(i).getCompanyName());
            System.out.println(brands.get(i).getOrdered());
            System.out.println(brands.get(i).getDescription());
            System.out.println(brands.get(i).getStatus());
            System.out.println(brands);
        }
        //释放资源
        sqlSession.close();
    }

    //单条件动态条件查询
    @Test
    public void testSelectByIdConditionSingle() throws IOException {
        //模拟接收数据
        int status = 1;
        String brandName = "小米";
        String companyName = "小米";
        //处理获得的参数
        brandName = "%" + brandName + "%";
        companyName = "%" + companyName + "%";
        //封装对象
        Brand brand = new Brand();
//        brand.setStatus(status);
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        List<Brand> brands = mapper.selectByConditionSingle(brand);
        for (int i = 0; i < brands.size(); i++) {
            System.out.println(brands.get(i).getId());
            System.out.println(brands.get(i).getBrandName());
            System.out.println(brands.get(i).getCompanyName());
            System.out.println(brands.get(i).getOrdered());
            System.out.println(brands.get(i).getDescription());
            System.out.println(brands.get(i).getStatus());
            System.out.println(brands);
        }
        //释放资源
        sqlSession.close();
    }

    //添加
    @Test
    public void testAdd() throws IOException {
        //模拟接收数据
        int status = 1;
        String brandName = "OPPO";
        String companyName = "OPPO有限公司";
        int ordered = 100;
        String description = "充电两分钟通话两小时";
        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象（SqlSession sqlSession = sqlSessionFactory.openSession()括号里面的true表示自动提交事务，false表示手动提交事务）
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        mapper.add(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //添加（获取主键_id）
    @Test
    public void testAdd1() throws IOException {
        //模拟接收数据
        int status = 1;
        String brandName = "OPPO";
        String companyName = "OPPO有限公司";
        int ordered = 100;
        String description = "充电两分钟通话两小时";
        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象（SqlSession sqlSession = sqlSessionFactory.openSession()括号里面的true表示自动提交事务，false表示手动提交事务）
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        mapper.add(brand);
        Integer id = brand.getId();
        System.out.println(id);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //修改（根据_id）
    @Test
    public void testUpdate() throws IOException {
        //模拟接收数据
        int status = 1;
        String brandName = "OPPO";
        String companyName = "OPPO有限公司";
        int ordered = 50;
        String description = "充电两分钟，通话两小时";
        int id = 7;
        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(ordered);
        brand.setDescription(description);
        brand.setId(id);
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象（SqlSession sqlSession = sqlSessionFactory.openSession()括号里面的true表示自动提交事务，false表示手动提交事务）
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        int count = mapper.update(brand);
        System.out.println(count);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //动态修改（根据_id）
    @Test
    public void testUpdate1() throws IOException {
        //模拟接收数据
        int status = 1;
//        String brandName = "OPPO";
        String companyName = "OPPO有限责任公司";
//        int ordered = 50;
//        String description = "充电两分钟，通话两小时";
        int id = 7;
        //封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
//        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
//        brand.setOrdered(ordered);
//        brand.setDescription(description);
        brand.setId(id);
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象（SqlSession sqlSession = sqlSessionFactory.openSession()括号里面的true表示自动提交事务，false表示手动提交事务）
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        int count = mapper.updateDT(brand);
        System.out.println(count);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //删除（根据_id）
    @Test
    public void testdeleteById() throws IOException {
        //模拟接收数据
        int id = 7;
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象（SqlSession sqlSession = sqlSessionFactory.openSession()括号里面的true表示自动提交事务，false表示手动提交事务）
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        mapper.deleteById(id);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    //批量删除（根据_id）
    @Test
    public void testdeleteById1() throws IOException {
        //模拟接收数据
        int[] ids={6,9};
        //获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession对象（SqlSession sqlSession = sqlSessionFactory.openSession()括号里面的true表示自动提交事务，false表示手动提交事务）
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //执行方法
        mapper.deleteByIds(ids);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }
}
