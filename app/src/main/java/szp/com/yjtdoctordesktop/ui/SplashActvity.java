package szp.com.yjtdoctordesktop.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import szp.com.yjtdoctordesktop.R;
import szp.com.yjtdoctordesktop.MainActivity;
import szp.com.yjtdoctordesktop.utils.ShareUtils;
import szp.com.yjtdoctordesktop.utils.StaticClass;

public class SplashActvity extends AppCompatActivity {

    private static final String TAG = "SplashActvity";

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if (isFirst()) {
                        Log.d(TAG, "handleMessage: 是第一次运行");
                        startActivity(new Intent(SplashActvity.this, LoginActivity.class));
                    } else {
                        Log.d(TAG, "handleMessage: 不是第一次运行");
                        startActivity(new Intent(SplashActvity.this, LoginActivity.class));
                    }
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_FIRST, true);
        if (isFirst) {
            ShareUtils.putBoolean(this, StaticClass.SHARE_IS_FIRST, false);
            //是第一次运行
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    //初始化View
    private void initView() {
        //延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
