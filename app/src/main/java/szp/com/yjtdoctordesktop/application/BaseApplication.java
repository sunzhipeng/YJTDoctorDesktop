package szp.com.yjtdoctordesktop.application;

import android.app.Application;
import android.util.Log;

import cn.bmob.v3.Bmob;
import szp.com.yjtdoctordesktop.utils.StaticClass;

/**
 * Created by 至鹏 on 2018/7/20.
 */

public class BaseApplication extends Application{

    private static final String TAG = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: Bmob init");
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);
    }
}
