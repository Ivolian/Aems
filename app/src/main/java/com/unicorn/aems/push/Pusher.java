package com.unicorn.aems.push;

import android.content.Context;

import com.unicorn.aems.app.dagger.App;

import java.util.Set;

import javax.inject.Inject;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

@App
public class Pusher {

    private Context context;

    @Inject
    public Pusher(Context context) {
        this.context = context;
    }

    public void setTags(Set<String> tags, TagAliasCallback callback) {
        JPushInterface.setTags(context, tags, callback);
    }

}
