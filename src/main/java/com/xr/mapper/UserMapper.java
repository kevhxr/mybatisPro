package com.xr.mapper;

import com.xr.entity.Stock;
import com.xr.entity.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserBean> findUserById(int userId);

    List<Stock> findStockById(int stockid);

    int updateStockById(int stockid);

    List<UserBean> findAllUsers();

    List<UserBean> findAllUsersFlushCache();

    int insertUser(UserBean userBean);

    int updateUser();

    int updateUserById(int userId);

    void batchInsert(@Param("userlist") List<UserBean> list);
}
