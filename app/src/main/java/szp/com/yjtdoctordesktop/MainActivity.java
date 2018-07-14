package szp.com.yjtdoctordesktop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.Bmob;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    private static final String APP_KEY = "ac789cf30663c1b23a509bdf97d0135e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bmob.initialize(this, APP_KEY);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        // 设置状态栏
        setStatusBar();
    }

    /**
     *  设置状态栏
     */
    private void setStatusBar() {
        // 设置状态栏颜色
        //int color = getResources().getColor(R.color.colorPrimary);
        //StatusBarUtil.setColor(MainActivity.this, color);
        // 设置状态栏全透明
        //StatusBarUtil.setTransparent(MainActivity.this);
        // 设置状态栏半透明
        //StatusBarUtil.setTranslucent(MainActivity.this, 5);
    }
}
