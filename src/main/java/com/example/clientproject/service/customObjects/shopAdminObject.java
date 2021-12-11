package com.example.clientproject.service.customObjects;

public class shopAdminObject {
    int userId;
    String shopName, userName;

    public shopAdminObject() {
        this.userId = 0;
        this.shopName = "";
        this.userName = "";
    }
    public String getShopName(){
        return this.shopName;
    }

    public String getUserName(){
        return this.userName;
    }

    public int getUserId(){
        return this.userId;
    }


    public shopAdminObject(int userId, String shopName, String userName) {
        this.userId = userId;
        this.shopName = shopName;
        this.userName = userName;
    }


}
