package com.qianfeng.kun.yuwei.other.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.qianfeng.kun.yuwei.R;
import com.qianfeng.kun.yuwei.other.utils.JumpManager;
import com.qianfeng.kun.yuwei.other.utils.YuWeiContants;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_label,iv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_label= (ImageView) findViewById(R.id.main_label_iv);
        iv_content= (ImageView) findViewById(R.id.main_content_iv);

        //初始化启动动画
        Animation labelAnim= AnimationUtils.loadAnimation(this,R.anim.anim_main_label_in);
        Animation contentAnim=AnimationUtils.loadAnimation(this,R.anim.anim_main_content_in);
        //给图片分别设置动画
        iv_label.startAnimation(labelAnim);
        iv_content.startAnimation(contentAnim);
        //给contentAnim设置动画监听
        contentAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isFirst()){
                    //如果是第一次使用，跳转欢迎界面
                    JumpManager.JumpToGuide(MainActivity.this);
                    finish();
                }else {
                    //否则直接跳转主页界面
                    JumpManager.JumpToHome(MainActivity.this);
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    /**
     * 设置共享参数
     * 判断是否第一次进入应用
     * @return
     */
    private Boolean isFirst(){

        SharedPreferences sharedPreferences=getSharedPreferences(YuWeiContants.GUIDE_FIRST_USED,MODE_PRIVATE);

        return sharedPreferences.getBoolean(YuWeiContants.GUIDE_FLAG_USED,true);
    }
}
