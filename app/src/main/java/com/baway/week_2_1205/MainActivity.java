package com.baway.week_2_1205;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baway.week_2_1205.adapter.MyFragmentPagerAdapter;
import com.baway.week_2_1205.fragment.QRCodeFragment;
import com.baway.week_2_1205.fragment.ShowFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mVP;
    /**
     * 我的数据
     */
    private RadioButton mBtnShow;
    /**
     * 我的名片
     */
    private RadioButton mBtnEwm;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragments;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化数据
        initView();
        //初始化数据
        initData();

    }

    private void initData() {
        //创建fragment集合
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new ShowFragment());
        mFragments.add(new QRCodeFragment());
        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),this, mFragments);
        mVP.setAdapter(mMyFragmentPagerAdapter);

        mVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mRadioGroup.check(mRadioGroup.getChildAt(i % mFragments.size()).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.btn_show:
                        mVP.setCurrentItem(0);
                        break;
                    case R.id.btn_ewm:
                        mVP.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    private void initView() {
        mVP = (ViewPager) findViewById(R.id.v_p);
        mBtnShow = (RadioButton) findViewById(R.id.btn_show);
        mBtnEwm = (RadioButton) findViewById(R.id.btn_ewm);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
    }
}
