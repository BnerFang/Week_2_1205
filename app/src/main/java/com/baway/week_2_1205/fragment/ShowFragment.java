package com.baway.week_2_1205.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baway.week_2_1205.R;
import com.baway.week_2_1205.adapter.MyNewsAdapter;
import com.baway.week_2_1205.bean.NewsBean;
import com.baway.week_2_1205.util.OkHttpUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public class ShowFragment extends Fragment {

    private View mView;
    private View view;
    private ListView mListView;
    private List<NewsBean.DataBean> mDataBeans =  new ArrayList<>();
    private MyNewsAdapter mMyNewsAdapter;

    private String newsPath = "http://www.xieast.com/api/news/news.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_show, container, false);
        //初始化控件
        initView(mView);
        return mView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化数据
        initData();

    }

    private void initData() {
        mMyNewsAdapter = new MyNewsAdapter(getActivity(), mDataBeans);
        mListView.setAdapter(mMyNewsAdapter);
        new OkHttpUtil().OkHttpGet(newsPath).setOkHttpListener(new OkHttpUtil.OkHttpListener() {
            @Override
            public void OkHttpSuccess(String data) {
                Gson gson = new Gson();
                mDataBeans = gson.fromJson(data,NewsBean.class).getData();
                mMyNewsAdapter.setList(mDataBeans);
                mMyNewsAdapter.notifyDataSetChanged();
            }

            @Override
            public void OkHttpError(String error) {

            }
        });

    }


    //初始化控件
    private void initView(View mView) {
        mListView = (ListView) mView.findViewById(R.id.list_view);
    }
}
