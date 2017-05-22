package com.unicorn.aems.push;

import android.content.Context;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.unicorn.aems.airport.entity.Airport;
import com.unicorn.aems.app.dagger.App;
import com.unicorn.aems.login.entity.SessionInfo;
import com.unicorn.aems.login.entity.UserInfo;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import cn.jpush.android.api.JPushInterface;

@App
public class PushUtils {

    private Context context;

    @Inject
    public PushUtils(Context context) {
        this.context = context;
    }

    public void setTags(@NonNull Airport airportSelected,@NonNull SessionInfo sessionInfo) {
        Set<String> tags = new HashSet<>();
        tags.add("aems");
        String airportCode = airportSelected.getCode();
        tags.add(airportCode);
        UserInfo userInfo = sessionInfo.getCurrentUser();
        tags.add(airportCode + idToTag(userInfo.getUserId()));
        tags.add(airportCode + idToTag(userInfo.getRoleId()));
        JPushInterface.setTags(context, tags, (responseCode, oldAlias, oldTags) -> {
//            // responseCode状态码：0为成功，其他返回码请参考错误码定义。
//            // http://docs.jiguang.cn/jpush/client/Android/android_api/#client_error_code
            if (responseCode != 0) {
                Logger.d("设置推送标签失败，错误码:" + responseCode);
            } else {
                Logger.d("设置推送标签成功");
            }
        });
    }

    private String idToTag(String id) {
        return id.replace("-", "_");
    }

}
