package com.xr.service;

import com.xr.entity.Stock;
import com.xr.entity.UserBean;
import com.xr.mapper.UserMapper;
import com.xr.mapper.UserTwoMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {

    Logger logger = LoggerFactory.getLogger(CacheService.class);

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    UserMapper mapper;

    @Autowired
    UserTwoMapper mapper2;


    public void cacheRefreshWhole() {

        //UserBean user = mapper.findUserById(571);
        List<Stock> stock = mapper.findStockById(2);
        logger.info("stock-{} : ", stock);

        //mapper.updateUserById(571);

        List<Stock> stock2 = mapper.findStockById(2);
        logger.info("stock-{} : ", stock2);
        System.out.println(stock.equals(stock2));
    }


    public void queryLevelTwoDiffNameSpace() {

        List<UserBean> user = mapper.findUserById(571);
        UserBean user2 = mapper2.findUserById(571);

        logger.info("user1-{} : user2-{}", user.get(0).getUserAlias(), user2.getUserAlias());

        mapper.updateUserById(571);

        List<UserBean> user1 = mapper.findUserById(571);
        UserBean user21 = mapper2.findUserById(571);

        logger.info("user1-{} : user2-{}", user1.get(0).getUserAlias(), user21.getUserAlias());
    }


    /**
     * 任何的 UPDATE, INSERT, DELETE 语句都会清空缓存。
     */
    public void queryWithSameSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<UserBean> allUsers = userMapper.findAllUsers();
        System.out.println("=============开始同一个 Sqlsession 的第二次查询============");
        List<UserBean> allUsers2 = userMapper.findAllUsers();
        System.out.println(allUsers.equals(allUsers2));
    }

    public void queryWithFlushCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<UserBean> allUsers = userMapper.findAllUsersFlushCache();
        System.out.println("=============开始同一个 Sqlsession 的第二次查询============");
        List<UserBean> allUsers2 = userMapper.findAllUsersFlushCache();
        System.out.println(allUsers.equals(allUsers2));
    }

    /**
     * 不同的 SqlSession 之间的缓存是相互隔离的；
     */
    public void queryWithDiffSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        List<UserBean> allUsers = userMapper.findAllUsers();
        System.out.println("=============开始同一个 Sqlsession 的第二次查询============");
        List<UserBean> allUsers2 = userMapper2.findAllUsers();
        System.out.println(allUsers.equals(allUsers2));
    }


    public void levelTwoCache() {
        // 使用 Mapper 接口的对应方法，查询 id=571 的对象
        List<UserBean> user = mapper.findUserById(571);
        System.out.println("user: " + user);
        // 更新对象的名称
        user.get(0).setUserName("milkshake");
        // 再次使用相同的 SqlSession 查询id=571 的对象
        List<UserBean> user1 = mapper.findUserById(571);
        System.out.println("milkshake".equals(user1.get(0).getUserName()));
        System.out.println("user1: " + user1);
        // 同一个 SqlSession ， 此时是一级缓存在作用， 两个对象相同
        System.out.println(user.equals(user1));

    }
}
