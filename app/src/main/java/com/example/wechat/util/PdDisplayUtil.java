package com.example.wechat.util;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wechat.R;

public class PdDisplayUtil {
    public static void passwordDisplay(Boolean showPassword, EditText editText, ImageView imageView) {
        if (showPassword) {
            imageView.setBackgroundResource(R.drawable.eye_o);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText.setSelection(editText.getText().toString().length());
        } else {
            imageView.setBackgroundResource(R.drawable.eye_c);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setSelection(editText.getText().toString().length());
        }
    }
}
