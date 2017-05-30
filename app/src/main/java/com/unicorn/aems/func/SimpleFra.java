package com.unicorn.aems.func;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseFra;
import com.unicorn.aems.constant.Key;
import com.unicorn.aems.menu.Menu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SimpleFra extends BaseFra {

    @Override
    protected int layoutResId() {
        return R.layout.fra_simple;
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    SimpleAdapter simpleAdapter = new SimpleAdapter();

    @Override
    protected void init() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(simpleAdapter);
        simpleAdapter.setNewData(getMenusEntitys());
    }

    private List<MenuEntity> getMenusEntitys(){
        ArrayList<Menu> menus = getMenus();

        List<MenuEntity> entities = new ArrayList<>();
        for (Menu menu:menus){
            MenuEntity header = new MenuEntity(true,menu.getName());
            entities.add(header);
            for (Menu child:menu.getChildList()){
                MenuEntity entity = new MenuEntity(child);
                entities.add(entity);
            }
        }
        return entities;
    }


    private ArrayList<Menu> getMenus(){
        return (ArrayList<Menu>)getArguments().getSerializable(Key.MENUS);
    }


}
