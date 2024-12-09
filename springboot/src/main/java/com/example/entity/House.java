package com.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class House implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String code;
    private Integer userId;
    private String building;
    private String unit;
    private String area;
    private String type;
    private String content;

    private String userName;



    @Override
    public int hashCode() {
        return Objects.hash(id, code, userId, building, unit, area, type, content, userName);
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", userId=" + userId +
                ", building='" + building + '\'' +
                ", unit='" + unit + '\'' +
                ", area='" + area + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}