package com.unicorn.aems.func.menu;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseFra;
import com.unicorn.aems.constant.Key;
import com.unicorn.aems.menu.Menu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MenuFra extends BaseFra {

    @Override
    protected int layoutResId() {
        return R.layout.fra_menu;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    MenuAdapter menuAdapter = new MenuAdapter();

    @Override
    protected void init() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.setNewData(menuSectionEntities());
        recyclerView.addItemDecoration(new SpaceItemDecoration(20));
    }

    private List<MenuSectionEntity> menuSectionEntities() {
        List<MenuSectionEntity> menuSectionEntities = new ArrayList<>();
        for (Menu menu : menus()) {
            menuSectionEntities.add(new MenuSectionEntity(true, menu.getName()));
            for (Menu child : menu.getChildList()) {
                menuSectionEntities.add(new MenuSectionEntity(child));
            }
        }
        return menuSectionEntities;
    }

    private ArrayList<Menu> menus(){
        return (ArrayList<Menu>)getArguments().getSerializable(Key.MENUS);
    }

}
