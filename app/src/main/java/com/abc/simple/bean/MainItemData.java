package com.abc.simple.bean;

import java.util.List;

/**
 * @name lz
 * @time 2019/7/1 10:12
 */
public class MainItemData {
    private String name;
    private String userPic;
    private String desc;
    private float money;

    public void setMoney(float money) {
        this.money = money;
    }

    public float getMoney() {
        return money;
    }

    private List<String> imgList;

    public String getName() {
        return name;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public String getDesc() {
        return desc;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
