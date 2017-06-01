package com.unicorn.aems.func.menu;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.unicorn.aems.menu.Menu;

public class MenuSectionEntity extends SectionEntity<Menu> {

    public MenuSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MenuSectionEntity(Menu menu) {
        super(menu);
    }

}
