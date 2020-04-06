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

@Service
public class TransFrontService {
    @Autowired
    UserService transService;

    @Autowired
    private UserMapper userMapper;
    public void callWithTx() {
        transService.insert();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void callWithoutTxSupport() {
        Map<Object, Object> map = TransactionSynchronizationManager.getResourceMap();
        if (map != null && !map.isEmpty()) {
            map.keySet().stream().forEach(key -> {
                ConnectionHolder o = (ConnectionHolder) map.get(key);
                try {
                    System.out.println(
                            "transactionB status: " +
                                    o.getConnection().getAutoCommit());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        } else {
            System.out.println(
                    "transactionB status: " + map);
        }
        //transService.insert();
    }

    public void callWithoutTxRequired() {
        transService.insert();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void callRequiredNew() {
        UserBean user = new UserBean("kevin",22,"xr");
        userMapper.insertUser(user);

        transService.insertRequiredNew();

        UserBean user2 = new UserBean("gbv",22,"xr");
        userMapper.insertUser(user2);
        throw new RuntimeException("eeeee");
    }

}
