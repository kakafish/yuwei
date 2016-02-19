package com.qianfeng.kun.yuwei.other.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qianfeng.kun.yuwei.R;
import com.qianfeng.kun.yuwei.other.adapter.GuideAdapter;
import com.qianfeng.kun.yuwei.other.utils.JumpManager;
import com.qianfeng.kun.yuwei.other.utils.YuWeiContants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liukun
 *
 * @date : 2016/2/15.10:23.
 */
public class GuideActivity extends Activity {

    private ViewPager viewPager;

    private Button button;

    private RadioGroup radioGroup;

    private GuideAdapter guideAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewPager= (ViewPager) findViewById(R.id.guide_content_vp);
        button= (Button) findViewById(R.id.guide_tiyan_btn);
        radioGroup= (RadioGroup) findViewById(R.id.guide_choose_rg);

        final List<ImageView> imageViewList=new ArrayList<>();
        int[] bitmaps=new int[]{
                R.drawable.meishituijian,
                R.drawable.fenxiangshiji,
                R.drawable.gongnengjieshao,
                R.drawable.quanqiufanyi
        };

        //给ImageView添加图片,将imageview放入集合中
        //给RadioGroup添加button
        for (int i = 0; i < bitmaps.length; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(bitmaps[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewList.add(imageView);

            RadioButton radioButton= (RadioButton) LayoutInflater.from(this).inflate(R.layout.guide_choose_radiobutton,null);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }
        //默认选中第一个radioButton
        RadioButton rb= (RadioButton) radioGroup.getChildAt(0);
        rb.performClick();
        //rb.setSelected(true);

        /**
         * 给radiogroup设置监听器，选中对应按钮的时候，页面也要对应变化
         */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //设置viewPager当前页面
                viewPager.setCurrentItem(checkedId);
            }
        });

        guideAdapter=new GuideAdapter(imageViewList);
        //给Viewpager设置适配器
        viewPager.setAdapter(guideAdapter);

        /**
         * 给button设置监听器，点击后改变共享参数，跳转主页面
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改共享参数
                SharedPreferences sharedPreferences = getSharedPreferences(YuWeiContants.GUIDE_FIRST_USED, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(YuWeiContants.GUIDE_FLAG_USED, false);
                editor.commit();

                //跳转主页界面
                JumpManager.JumpToHome(GuideActivity.this);
                finish();

            }
        });

        /**
         * 给ViewPager设置监听器，在最后一页让Button显示
         * 页面滑动，radioButton选中也相应改变
         */
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                //当页面滑动，radioButton选中也相应改变
                RadioButton rb= (RadioButton) radioGroup.getChildAt(position);
                //rb.setSelected(true);
                rb.performClick();

                //当滑动到最后一个页面，Button显示
                if (position==imageViewList.size()-1){
                    button.setVisibility(View.VISIBLE);
                }else {

                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
