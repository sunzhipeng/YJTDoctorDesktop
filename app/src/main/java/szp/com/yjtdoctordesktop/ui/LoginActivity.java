package szp.com.yjtdoctordesktop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import szp.com.yjtdoctordesktop.MainActivity;
import szp.com.yjtdoctordesktop.R;
import szp.com.yjtdoctordesktop.entity.MyUser;
import szp.com.yjtdoctordesktop.utils.ShareUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private Button btn_login;
    private Button btn_registered;
    private CheckBox keep_password;
    private TextView tv_forget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }


    private void initView() {
        et_name = findViewById(R.id.user_name);
        et_password = findViewById(R.id.user_password);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        btn_registered = findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);

        keep_password = findViewById(R.id.keep_password);
        tv_forget = findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);

        boolean isKeep = ShareUtils.getBoolean(this, "keeppass", false);
        keep_password.setChecked(isKeep);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                //1.获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //2.判断是否为空
                if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(password)) {
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            if (e == null) {
                                if (user.getEmailVerified()) {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "请前往邮箱验证", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_registered:
                startActivity(new Intent(LoginActivity.this, RegisteredActivity.class));
                break;

            case R.id.tv_forget:
                break;
        }
    }


    //假设我现在输入用户名和密码，但是我不点击登录，而是直接退出了
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存状态
        ShareUtils.putBoolean(this, "keeppass", keep_password.isChecked());

        //是否记住密码
        if (keep_password.isChecked()) {
            //记住用户名和密码
            ShareUtils.putString(this, "name", et_name.getText().toString().trim());
            ShareUtils.putString(this, "password", et_password.getText().toString().trim());
        } else {
            ShareUtils.deleShare(this, "name");
            ShareUtils.deleShare(this, "password");
        }
    }
}
