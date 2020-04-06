package com.xr.service.modules;


import com.xr.entity.UserBean;
import com.xr.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insert() {
        Map<Object, Object> map = TransactionSynchronizationManager.getResourceMap();
        if (map != null && !map.isEmpty()) {
            map.keySet().stream().forEach(key -> {
                ConnectionHolder o = (ConnectionHolder) map.get(key);
                try {
                    System.out.println(
                            "transaction status: " +
                                    o.getConnection().getAutoCommit());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }
        UserBean user = new UserBean();
        Random random = new Random();
        random.nextInt(10);
        user.setUserAge(5 * random.nextInt(10));
        user.setUserAlias("el chapo");
        user.setUserName("guzman");
        userMapper.insertUser(user);
        throw new RuntimeException("eeeee");
    }

    //@Transactional
    public void inserBatch() {
        for (int i = 0; i < 10; i++) {
            if (i == 9) {
                throw new RuntimeException("blabla");
            }
            insert();
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiredNew() {
        UserBean user2 = new UserBean("martin",42,"steer");
        userMapper.insertUser(user2);
    }
}
