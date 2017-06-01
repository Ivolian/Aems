package com.unicorn.aems.func;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.unicorn.aems.R;
import com.unicorn.aems.base.BaseFra;
import com.unicorn.aems.constant.Key;
import com.unicorn.aems.menu.Menu;

import java.util.ArrayList;

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
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(menuAdapter);
        menuAdapter.setNewData(menus());
    }




    private ArrayList<Menu> menus(){
        return (ArrayList<Menu>)getArguments().getSerializable(Key.MENUS);
    }


}
