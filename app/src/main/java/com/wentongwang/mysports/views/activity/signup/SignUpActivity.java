package com.wentongwang.mysports.views.activity.signup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.wentongwang.mysports.R;
import com.wentongwang.mysports.base.GetPictureBaseActivity;
import com.wentongwang.mysports.views.activity.home.HomeActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Wentong WANG on 2016/9/8.
 */
public class SignUpActivity extends GetPictureBaseActivity implements SignUpView {


    @BindView(R.id.root_view)
    protected View rootView;

    @BindView(R.id.iv_sign_up_user_head)
    protected ImageView userHead;
    @BindView(R.id.et_sign_up_username)
    protected EditText userName;
    @BindView(R.id.et_sign_up_pwd)
    protected EditText userPwd;
    @BindView(R.id.et_sign_up_pwd_confirm)
    protected EditText userPwd2;
    @BindView(R.id.et_sign_up_email)
    protected EditText userEmail;
    @BindView(R.id.rg_sign_up_select_sex)
    protected RadioGroup mRadioGroup;


    private int userSex = 0; //0 = male; 1 = female
    private SignUpPresenter mPresenter = new SignUpPresenter(this);
    private Uri imageUri;
    private File outputFile;

    @Override
    protected boolean notitle() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sginup_layout;
    }

    @Override
    protected void initDatasAndViews() {
        mPresenter.init(SignUpActivity.this);

        File outputFileDir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "userHead");
        if (!outputFileDir.exists()) {
            outputFileDir.mkdirs();
        }
        try {
            outputFile = File.createTempFile("user_head",".jpg", outputFileDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputFile);
    }

    @Override
    protected void initEvents() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_male) {
                    userSex = 0;
                } else {
                    userSex = 1;
                }
            }
        });

    }


    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getUserPwd() {
        return userPwd.getText().toString();
    }

    @Override
    public String getUserPwd2() {
        return userPwd2.getText().toString();
    }

    @Override
    public String getUserEmail() {
        return userEmail.getText().toString();
    }

    @Override
    public String getUserSex() {
        return Integer.toString(userSex);
    }

    @Override
    public void goToHomeActivity() {
        Intent it = new Intent();
        it.setClass(SignUpActivity.this, HomeActivity.class);
        startActivity(it);
    }

    @Override
    public void showPopupWindow(PopupWindow popupWindow) {
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void setBackGroundAlpha(float alpha) {
        WindowManager.LayoutParams windowlp = this.getWindow().getAttributes();
        windowlp.alpha = alpha;
        this.getWindow().setAttributes(windowlp);
    }

    @Override
    public View initPopupView() {
        //TODO: 重构：之后需要提取出来，因为跟HomeActivity里的是一样的逻辑
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_chose_layout, null);

        Button eventBtn = (Button) popupView.findViewById(R.id.btn_creat_event);
        eventBtn.setText("相机");
        Button postBtn = (Button) popupView.findViewById(R.id.btn_creat_post);
        postBtn.setText("相册");

        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto();
            }
        });
        return popupView;
    }

    @OnClick(R.id.iv_sign_up_user_head)
    public void chooseUserHead() {
        mPresenter.popupChoseWindow();
    }

    @OnClick(R.id.btn_sign_in)
    public void signUp() {
        mPresenter.signUp();
    }


    @Override
    protected ImageView getImageView() {
        return userHead;
    }

    @Override
    protected void imagesPicked(List<Bitmap> bitmaps) {

    }

}
