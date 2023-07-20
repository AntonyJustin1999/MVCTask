package com.test.app2.loadmaps.view;

import static com.test.app2.loadmaps.MyApplication.getRealmDBAdapterInstance;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.test.app2.R;
import com.test.app2.loadmaps.Activities.HomeActivity;
import com.test.app2.loadmaps.Activities.LoginActivity;
import com.test.app2.loadmaps.Activities.RegisterActivity;
import com.test.app2.loadmaps.controller.MVCLoginController;
import com.test.app2.loadmaps.controller.MVCSplashScreenController;
import com.test.app2.loadmaps.model.MVCModelImplementor;

public class MVCViewLoginImpl implements MVCViewLogin{

    View rootView;
    AppCompatActivity activity;
    MVCLoginController mvcLoginController;
    MVCModelImplementor mvcModel;

    private EditText etUserName,etPassword;
    private Boolean IsPwdShow = false;
    private ImageView iv_pwd_eye;
    private Button btnLogin;
    private TextView tv_register;
    private LottieAnimationView progress_bar;
    private Context context;

    public MVCViewLoginImpl (Context context, AppCompatActivity activity, ViewGroup continer){
        this.activity = activity;
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.login_activity,continer);
        mvcModel = new MVCModelImplementor(getRealmDBAdapterInstance());
        mvcLoginController = new MVCLoginController(mvcModel,this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setHomeButtonEnabled(false);

        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        etUserName = rootView.findViewById(R.id.et_userName);
        etPassword = rootView.findViewById(R.id.et_password);
        btnLogin = rootView.findViewById(R.id.btn_login);
        progress_bar = rootView.findViewById(R.id.progress_bar);

        iv_pwd_eye = rootView.findViewById(R.id.iv_password_eye);

        iv_pwd_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsPwdShow){
                    iv_pwd_eye.setImageResource(R.drawable.ic_eye_off);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPassword.setSelection(etPassword.length());
                    IsPwdShow = !IsPwdShow;
                } else {
                    iv_pwd_eye.setImageResource(R.drawable.ic_eye);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPassword.setSelection(etPassword.length());
                    IsPwdShow = !IsPwdShow;
                }
            }
        });

        if(IsPwdShow){
            iv_pwd_eye.setImageResource(R.drawable.ic_eye);
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            iv_pwd_eye.setImageResource(R.drawable.ic_eye_off);
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvcLoginController.ValidateLogin(etUserName.getText().toString().trim(),etPassword.getText().toString().trim());
            }
        });

        tv_register = rootView.findViewById(R.id.tv_register);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationToRegister();
            }
        });
    }

    @Override
    public void navigationToRegister() {
        Intent intent = new Intent(rootView.getContext(), RegisterActivity.class);
        rootView.getContext().startActivity(intent);
        activity.finish();
    }

    @Override
    public void navigationToHome() {
        Intent intent = new Intent(rootView.getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        rootView.getContext().startActivity(intent);
        activity.finish();
    }

    @Override
    public void showprogressbar() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideprogressbar() {
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String ErrorMessage) {
        showalertDialogBox("",ErrorMessage);
    }

    public void showalertDialogBox(String title,String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
