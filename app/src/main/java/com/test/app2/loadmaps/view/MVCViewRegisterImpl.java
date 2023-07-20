package com.test.app2.loadmaps.view;

import static com.test.app2.loadmaps.MyApplication.getRealmDBAdapterInstance;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.test.app2.R;
import com.test.app2.loadmaps.Activities.HomeActivity;
import com.test.app2.loadmaps.Activities.LoginActivity;
import com.test.app2.loadmaps.Activities.RegisterActivity;
import com.test.app2.loadmaps.DataSets.UserDataSet;
import com.test.app2.loadmaps.controller.MVCLoginController;
import com.test.app2.loadmaps.controller.MVCRegisterController;
import com.test.app2.loadmaps.model.MVCModelImplementor;

public class MVCViewRegisterImpl implements MVCViewRegister{

    View rootView;
    AppCompatActivity activity;
    MVCRegisterController mvcRegisterController;
    MVCModelImplementor mvcModel;


    private EditText etUserName,etPassword,et_mobile_number,et_con_password,etEmail;
    private Boolean IsPwdShow = false,IsPwdShowCon = false;
    private ImageView iv_pwd_eye,iv_pwd_eye_con;
    private Button btnRegister;
    private TextView tv_login;
    private LottieAnimationView progress_bar;
    private Context context;


    public MVCViewRegisterImpl(Context context, AppCompatActivity activity, ViewGroup continer){
        this.activity = activity;
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.register_activity,continer);
        mvcModel = new MVCModelImplementor(getRealmDBAdapterInstance());
        mvcRegisterController = new MVCRegisterController(mvcModel,this);
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

        etUserName = rootView.findViewById(R.id.et_username);
        etEmail = rootView.findViewById(R.id.et_email);
        et_mobile_number = rootView.findViewById(R.id.et_mobile_number);
        etPassword = rootView.findViewById(R.id.et_password);
        et_con_password = rootView.findViewById(R.id.et_con_passwrod);
        tv_login = rootView.findViewById(R.id.tv_login);
        btnRegister = rootView.findViewById(R.id.btn_register);
        progress_bar = rootView.findViewById(R.id.progress_bar);

        //showAlertDialog("test title","test message")

        iv_pwd_eye = rootView.findViewById(R.id.iv_password_eye);

        iv_pwd_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwdShowandHideClick();
            }
        });

        if (IsPwdShow) {
            iv_pwd_eye.setImageResource(R.drawable.ic_eye);
            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            iv_pwd_eye.setImageResource(R.drawable.ic_eye_off);
            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }


        iv_pwd_eye_con = rootView.findViewById(R.id.iv_password_eye1);

        iv_pwd_eye_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conpwdShowandHideClick();
            }
        });

        if (IsPwdShowCon) {
            iv_pwd_eye_con.setImageResource(R.drawable.ic_eye);
            et_con_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            iv_pwd_eye_con.setImageResource(R.drawable.ic_eye_off);
            et_con_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDataSet userDataSet = new UserDataSet();
                userDataSet.setEmailId(etEmail.getText().toString().trim());
                userDataSet.setUserName(etUserName.getText().toString().trim());
                userDataSet.setMobileNumber(et_mobile_number.getText().toString().trim());
                userDataSet.setPassword(etPassword.getText().toString().trim());
                userDataSet.setConpassword(et_con_password.getText().toString().trim());
                mvcRegisterController.OnRegister(userDataSet);
            }
        });

        tv_login = rootView.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationToLogin();
            }
        });
    }

    @Override
    public void navigationToLogin() {
        Intent intent = new Intent(rootView.getContext(), LoginActivity.class);
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

    public void pwdShowandHideClick() {
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
    public void conpwdShowandHideClick() {
        if(IsPwdShowCon){
            iv_pwd_eye_con.setImageResource(R.drawable.ic_eye_off);
            et_con_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            et_con_password.setSelection(et_con_password.length());
            IsPwdShowCon = !IsPwdShowCon;
        } else {
            iv_pwd_eye_con.setImageResource(R.drawable.ic_eye);
            et_con_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            et_con_password.setSelection(et_con_password.length());
            IsPwdShowCon = !IsPwdShowCon;
        }
    }
}
