package com.demon.tempkeyboard;

import android.annotation.SuppressLint;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.demon.tempkeyboard.keyboard.KeyboardTEMPHelper;

public class MainActivity extends AppCompatActivity {

    private KeyboardTEMPHelper helper;
    private EditText editText;
    private KeyboardView keyboard;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keyboard = findViewById(R.id.keyboard_temp);
        editText = findViewById(R.id.et);
        //初始化KeyboardView
        helper = new KeyboardTEMPHelper(MainActivity.this, keyboard);
        //设置editText与KeyboardView绑定
        helper.setEditText(editText);
        helper.setCallBack(new KeyboardTEMPHelper.KeyboardCallBack() {
            @Override
            public void keyCall(int code) {
               //回调键盘监听，根据回调的code值进行处理
            }
        });
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //多条件判断，防止重复显示
                if (editText.hasFocus() && !helper.isVisibility() && event.getAction() == MotionEvent.ACTION_DOWN) {
                    helper.show();
                }
                return false;
            }
        });
    }
}
