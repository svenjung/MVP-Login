package com.alashoo.signmvp.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class User {
    // 用戶id
    private String id;
    // 昵稱
    @JSONField(name = "nikename")
    private String nikeName;
    // 手机号码
    private String mobile;
    // 是否已实名认证
    private boolean auth;
    // 性别
    private int gender;
    // 邮箱地址
    private String email;
    // 头像地址
    private String avatar;
    // 真实姓名
    private String realName;
    // 位置信息
    private Long latitude;
    private Long longitude;

    // 是否设置支付密码
    private boolean hasPayPassword;

    private boolean isFriend;

    private int addressCode;
    private int hometownCode;
    private List<String> address;
    private List<String> hometown;

    private List<Language> languages;

    private String hxId;
    private String hxPwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public boolean isHasPayPassword() {
        return hasPayPassword;
    }

    public void setHasPayPassword(boolean hasPayPassword) {
        this.hasPayPassword = hasPayPassword;
    }

    public boolean isFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public int getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(int addressCode) {
        this.addressCode = addressCode;
    }

    public int getHometownCode() {
        return hometownCode;
    }

    public void setHometownCode(int hometownCode) {
        this.hometownCode = hometownCode;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }

    public List<String> getHometown() {
        return hometown;
    }

    public void setHometown(List<String> hometown) {
        this.hometown = hometown;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getHxId() {
        return hxId;
    }

    public void setHxId(String hxId) {
        this.hxId = hxId;
    }

    public String getHxPwd() {
        return hxPwd;
    }

    public void setHxPwd(String hxPwd) {
        this.hxPwd = hxPwd;
    }
}
