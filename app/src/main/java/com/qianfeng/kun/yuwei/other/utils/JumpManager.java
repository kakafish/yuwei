package com.qianfeng.kun.yuwei.other.utils;

import android.app.Activity;
import android.content.Intent;

import com.qianfeng.kun.yuwei.other.ui.GuideActivity;
import com.qianfeng.kun.yuwei.other.ui.HomeActivity;

/**
 * Created by liukun
 *
 * @date : 2016/2/15.14:36.
 */
public class JumpManager {
    /**
     * 跳转欢迎界面
     */
    public static void JumpToGuide(Activity activity){

        Intent intent=new Intent(activity, GuideActivity.class);
        activity.startActivity(intent);
    }

    /**
     * 跳转主页界面
     */
    public static void JumpToHome(Activity activity){

        Intent intent=new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

}
