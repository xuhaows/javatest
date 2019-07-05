package com.gsta.app.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.gsta.app.utils.DateUtil;
import com.gsta.app.utils.DateUtil.DateFormatType;

public class UserInfo {
    //Id
    private Integer id;
    //用户账户
    private String userId;
    //手机号码
    private String phoneNum;
    //微信openId
    private String wxAccount;
    //绑定时间
    private Date relatedTime;
    //最近访问时间
    private Date lastAccessTime;
    //用户类型
    private Integer type;
    //用户状态
    private Integer status;

    private static Map<Integer, String> typeMap = new HashMap<Integer, String>();
    private static Map<Integer, String> statusMap = new HashMap<Integer, String>();

    private Integer page;
    private Integer perPage;
    private Date beginTime;
    private Date endTime;

    static {
        typeMap.put(0, "渔场主");
        typeMap.put(1, "专家");
        typeMap.put(2, "系统管理员");
        typeMap.put(3, "超级渔场管理员");
        typeMap.put(4, "渔池管理员");

        statusMap.put(0, "正常");
        statusMap.put(1, "注销");
        statusMap.put(2, "异常");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getWxAccount() {
        return wxAccount;
    }

    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    public Date getRelatedTime() {
        return relatedTime;
    }

    public void setRelatedTime(Date relatedTime) {
        this.relatedTime = relatedTime;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static Map<Integer, String> getTypeMap() {
        return typeMap;
    }

    public static Map<Integer, String> getStatusMap() {
        return statusMap;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        if (beginTime != null && beginTime.length() > 0) {
            this.beginTime = DateUtil.formatStrToDate(beginTime,
                    DateFormatType.DATE_FORMAT_STR1);
        }
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if (endTime != null && endTime.length() > 0) {
            this.endTime = DateUtil.formatStrToDate(endTime,
                    DateFormatType.DATE_FORMAT_STR1);
        }
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}
