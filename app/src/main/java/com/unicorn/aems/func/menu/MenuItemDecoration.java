package com.unicorn.aems.func.menu;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;

import static android.support.v7.widget.RecyclerView.NO_POSITION;

public class MenuItemDecoration extends RecyclerView.ItemDecoration {

    private MenuAdapter menuAdapter;
    private int space = ConvertUtils.dp2px(8);

    public MenuItemDecoration(MenuAdapter menuAdapter) {
        this.menuAdapter = menuAdapter;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = parent.getChildLayoutPosition(view);
        if (position == NO_POSITION) {
            return;
        }
        MenuSectionEntity menuSectionEntity = menuAdapter.getItem(position);
        if (menuSectionEntity.isHeader) {
            return;
        }
        outRect.left = space;
        outRect.bottom = space;
        outRect.right= space;
        outRect.top= space;
    }

}