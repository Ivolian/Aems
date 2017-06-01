package com.unicorn.aems.func.menu;

import java.io.Serializable;
import java.util.ArrayList;

public class Menu implements Serializable {

    private String objectId;

    private String name;

    private ArrayList<Menu> childList;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Menu> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<Menu> childList) {
        this.childList = childList;
    }
}
