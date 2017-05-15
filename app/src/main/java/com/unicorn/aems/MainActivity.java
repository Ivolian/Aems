package com.unicorn.aems;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.unicorn.aems.app.dagger.AppComponentProvider;
import com.unicorn.aems.push.PushUtils;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppComponentProvider.provide().inject(this);

        setTags();
    }

    @Inject
    PushUtils pushUtils;

    private void setTags() {
        Set<String> tags = new HashSet<>();
        tags.add("-----");
        pushUtils.setTags(tags);
    }

}
