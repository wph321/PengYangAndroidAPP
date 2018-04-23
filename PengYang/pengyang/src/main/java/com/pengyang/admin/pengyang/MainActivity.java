package com.pengyang.admin.pengyang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView login_regist_Button = findViewById(R.id.login_regist_Button);//获取登录注册文本
        login_regist_Button.setOnClickListener(new View.OnClickListener() {//添加点击事件
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });


    }
}
