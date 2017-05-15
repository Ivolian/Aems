package com.unicorn.aems;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.push.Pusher;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppComponentProvider.provide().inject(this);

        setPushTags();
    }

    @Inject
    Pusher pusher;

    private void setPushTags() {
        Set<String> tags = new HashSet<>();
        tags.add("IIII");
        pusher.setTags(tags, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                String tt = "";
            }
        });
    }

}
