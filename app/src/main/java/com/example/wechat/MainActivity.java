package com.example.wechat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wechat.Beans.DataBeans;
import com.example.wechat.Beans.LoginBeans;
import com.example.wechat.util.AsyncTaskUtil;
import com.example.wechat.util.DataUtil;
import com.example.wechat.util.PdDisplayUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_user,et_pd;
    TextView register;
    Button sub;
    ImageView display;
    private AsyncTaskUtil loginAs;
    private DataUtil dataUtil = new DataUtil();
    private Boolean showPassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }

    private void init() {
        et_user = findViewById(R.id.username);
        et_pd = findViewById(R.id.password);
        register = findViewById(R.id.register);
        sub = findViewById(R.id.sub);
        display = findViewById(R.id.display);
        sub.setOnClickListener(this);
        register.setOnClickListener(this);
        display.setOnClickListener(this);

        String user = getIntent().getStringExtra("user");
        if (user == null) {
            SharedPreferences sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
            user = sharedPreferences.getString("user","");
            String password = sharedPreferences.getString("password","");
            et_user.setText(user);
            et_pd.setText(password);
        }else {
            et_user.setText(user);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sub:
                login();
                break;
            case R.id.register:
                startActivity(new Intent(MainActivity.this,Register.class));
                break;
            case R.id.display:
                PdDisplayUtil.passwordDisplay(showPassword,et_pd,display);
                showPassword = !showPassword;
                break;
        }
    }

    public void login() {
        final String user = et_user.getText().toString().trim();
        final String password = et_pd.getText().toString().trim();
        if (!user.isEmpty() && !password.isEmpty()) {
            loginAs = new AsyncTaskUtil(user,password);
            loginAs.AsyncTaskBeans(this, new AsyncTaskUtil.DataCallback() {
                @Override
                public void onSuccess(DataBeans dataBeans) {
                    LoginBeans loginBeans = dataBeans.getLoginBeans();
                    if (loginBeans.getStatus().equals("登陆成功")) {
                        dataUtil.LoginStorage(MainActivity.this,user,password);
                        Intent intent = new Intent(MainActivity.this,Welcome.class);
                        intent.putExtra("user",user);
                        intent.putExtra("name",loginBeans.getName());
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "账号或密码有误", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(Exception ex) {
                }
            },"");
        } else {
            Toast.makeText(this, "账号或密码为空", Toast.LENGTH_SHORT).show();
        }
    }

}
