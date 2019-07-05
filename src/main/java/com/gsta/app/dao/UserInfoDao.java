package com.gsta.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gsta.app.entity.UserInfo;

public interface UserInfoDao {
    public void save(UserInfo user);

    public boolean update(UserInfo user);

    public UserInfo getUserInfoByPhoneNum(@Param("phoneNum") String phoneNum);

    public UserInfo getUserInfoByWxAccount(@Param("wxAccount") String wxAccount);

    public List<UserInfo> getUserInfoAll(UserInfo userInfo) throws Exception;

    public Long getUserInfoCount(UserInfo userInfo) throws Exception;
}
