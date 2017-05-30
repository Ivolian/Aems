package com.unicorn.aems.func;


import com.chad.library.adapter.base.entity.SectionEntity;
import com.unicorn.aems.menu.Menu;

public class MenuEntity extends SectionEntity<Menu> {

    public MenuEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MenuEntity(Menu menu) {
        super(menu);
    }

}
