package com.baway.week_2_1205.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.week_2_1205.R;
import com.baway.week_2_1205.bean.NewsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 作者:  方诗康
 * 描述:
 */
public class MyNewsAdapter extends BaseAdapter {

    private Context mContext;
    private List<NewsBean.DataBean> mDataBeans;

    public MyNewsAdapter(Context context, List<NewsBean.DataBean> dataBeans) {
        mContext = context;
        mDataBeans = dataBeans;
    }

    public void setList(List<NewsBean.DataBean> dataBeans){
        this.mDataBeans = dataBeans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewholder viewholder;
        if (view==null) {
            view = View.inflate(mContext, R.layout.news_item_view,null);
            viewholder = new MyViewholder();
            viewholder.mTextViewTitle = view.findViewById(R.id.text_title);
            viewholder.mTextViewDesc = view.findViewById(R.id.text_desc);
            viewholder.mImageView1 = view.findViewById(R.id.img_icon1);
            viewholder.mImageView2 = view.findViewById(R.id.img_icon2);
            viewholder.mImageView3 = view.findViewById(R.id.img_icon3);
            view.setTag(viewholder);
        }else {
            viewholder = (MyViewholder) view.getTag();
        }
        viewholder.mTextViewTitle.setText(mDataBeans.get(i).getTitle());
        viewholder.mTextViewDesc.setText(mDataBeans.get(i).getCategory());
        ImageLoader.getInstance().displayImage(mDataBeans.get(i).getThumbnail_pic_s(),viewholder.mImageView1);
        ImageLoader.getInstance().displayImage(mDataBeans.get(i).getThumbnail_pic_s02(),viewholder.mImageView2);
        ImageLoader.getInstance().displayImage(mDataBeans.get(i).getThumbnail_pic_s03(),viewholder.mImageView3);
        return view;
    }

    class MyViewholder{
        TextView mTextViewTitle,mTextViewDesc;
        ImageView mImageView1,mImageView2,mImageView3;
    }
}
