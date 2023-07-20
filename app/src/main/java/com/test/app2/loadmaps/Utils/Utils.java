package com.test.app2.loadmaps.Utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Utils {
    public static Boolean isValidEmail(CharSequence target){
        if (TextUtils.isEmpty(target)) {
           return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
