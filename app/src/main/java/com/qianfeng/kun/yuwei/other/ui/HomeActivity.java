package com.qianfeng.kun.yuwei.other.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.qianfeng.kun.yuwei.R;
import com.qianfeng.kun.yuwei.mine.ui.MineFragment;
import com.qianfeng.kun.yuwei.moment.ui.MomentFragment;
import com.qianfeng.kun.yuwei.note.ui.NoteFragment;
import com.qianfeng.kun.yuwei.recommend.ui.RecommendFragment;
import com.qianfeng.kun.yuwei.world.ui.WorldFragment;

/**
 * Created by liukun
 *
 * @date : 2016/2/15.10:34.
 */
public class HomeActivity extends FragmentActivity {

    private FrameLayout frameLayout;
    private Fragment[] fragments;

    private RadioGroup radioGroup;

    //此处等于1是为了避免在监听中将id=0处fragment隐藏
    private int checkIndex=1;

    private RadioGroup.OnCheckedChangeListener changeListener=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //给RadioGroup中的RadioButton设置id
            int index=0;
            switch (checkedId){
                case R.id.home_rb_recommend:
                    index=0;
                    break;
                case R.id.home_rb_world:
                    index=1;
                    break;
                case R.id.home_rb_moment:
                    index=2;
                    break;
                case R.id.home_rb_note:
                    index=3;
                    break;
                case R.id.home_rb_mine:
                    index=4;
                    break;
                default:
                    index=0;
                    break;

            }
            //通过RadioButton的id来对应显示Fragment
            FragmentManager manager=getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.show(fragments[index]);
            //transaction.replace(R.id.home_fl,fragments[index]);
            //隐藏上一个fragment
            transaction.hide(fragments[checkIndex]);
            transaction.commit();
            checkIndex=index;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout= (FrameLayout) findViewById(R.id.home_fl);
        radioGroup= (RadioGroup) findViewById(R.id.home_rg);
        //给radioGroup设置监听
        radioGroup.setOnCheckedChangeListener(changeListener);
        //得到第一个radioButton
        View firstbtn = radioGroup.getChildAt(0);
        //将fragment存入数组中
        fragments=new Fragment[]{
                new RecommendFragment(),
                new WorldFragment(),
                new MomentFragment(),
                new NoteFragment(),
                new MineFragment()
        };
        //将对应fragment加入对应位置
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {

            Fragment fragment=fragments[i];
            transaction.add(R.id.home_fl,fragment);
            //隐藏
            transaction.hide(fragment);
        }
        //默认显示第一个fragment
        // transaction.replace(R.id.home_fl,fragments[0]);
        transaction.show(fragments[0]);
        transaction.commit();
        //默认显示第一个radiobutton
        firstbtn.performClick();
    }
}
