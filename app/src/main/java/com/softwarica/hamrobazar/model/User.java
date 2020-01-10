package com.softwarica.hamrobazar.model;

public class User {

    private String email;
    private String fullname;
    private String password;
    private String conpassword;
    private String phone;
    private String mobile;
    private String address1;
    private String address2;
    private String address3;
    private String image;

    public User(String email, String fullname, String password, String conpassword, String phone, String mobile, String address1, String address2, String address3, String image) {
        this.email = email;
        this.fullname = fullname;
        this.password = password;
        this.conpassword = conpassword;
        this.phone = phone;
        this.mobile = mobile;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpassword() {
        return conpassword;
    }

    public void setConpassword(String conpassword) {
        this.conpassword = conpassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
