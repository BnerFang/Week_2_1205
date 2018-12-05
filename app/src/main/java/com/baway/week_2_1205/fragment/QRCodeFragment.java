package com.baway.week_2_1205.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.week_2_1205.R;
import com.baway.week_2_1205.activity.LoginActivity;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

import static android.content.Context.MODE_PRIVATE;

/**
 * 作者:  方诗康
 * 描述:
 */
public class QRCodeFragment extends Fragment implements View.OnClickListener {

    private View mView;
    private RelativeLayout mQrView;
    LoginActivity mLoginActivity;
    /**
     * 退出登录
     */
    private Button mBtnTui;
    private ImageView mQrImg;
    private SharedPreferences mSP;
    private SharedPreferences.Editor mEdit;
    private String mMobile ="13800138000";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_qr, container, false);
        initView(mView);
        return mView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataZxing();
        Bundle bundle = getArguments();
        Toast.makeText(getActivity(), mMobile, Toast.LENGTH_LONG).show();
        mSP = getActivity().getSharedPreferences("config", MODE_PRIVATE);
        mEdit = mSP.edit();
    }

    private void initDataZxing() {
        QRTask qrTask = new QRTask(getActivity(), mQrImg, mMobile);//13800138000
        qrTask.execute(mMobile);
    }

    static class QRTask extends AsyncTask<String, Void, Bitmap> {
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> mImageView;

        public QRTask(Context context, ImageView imageView, String string) {
            mContext = new WeakReference<>(context);
            mImageView = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String str = strings[0];
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            int size = 200;
            return QRCodeEncoder.syncEncodeQRCode(str, size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                mImageView.get().setImageBitmap(bitmap);
            } else {
                Toast.makeText(mContext.get(), "生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView(View mView) {
        mQrView = (RelativeLayout) mView.findViewById(R.id.qr_view);
        mBtnTui = (Button) mView.findViewById(R.id.btn_tui);
        mBtnTui.setOnClickListener(this);
        mQrView.setOnClickListener(this);
        mQrImg = (ImageView) mView.findViewById(R.id.qr_img);
        mQrImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_tui:
                //清空所记住的记录
                mEdit.clear();
                mEdit.commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

}
