package com.unicorn.aems.push;

import android.content.Context;

import com.unicorn.aems.app.dagger.App;
import com.unicorn.aems.utils.ToastUtils;

import java.util.Set;

import javax.inject.Inject;

@App
public class PushUtils {

    private Context context;
    private ToastUtils toastUtils;

    @Inject
    public PushUtils(Context context, ToastUtils toastUtils) {
        this.context = context;
        this.toastUtils = toastUtils;
    }

    public void setTags(Set<String> tags) {
//        setTags(tags, (responseCode, alias, tags1) -> {
//            // alias原设置的别名，tags1原设置的标签，无视。
//            // responseCode状态码：0为成功，其他返回码请参考错误码定义。
//            // http://docs.jiguang.cn/jpush/client/Android/android_api/#client_error_code
//            if (responseCode != 0) {
//                toastUtils.show("设置推送标签失败，错误码:" + responseCode);
//            }
//        });
    }

//    public void setTags(Set<String> tags, TagAliasCallback callback) {
//        JPushInterface.setTags(context, tags, callback);
//    }

}
