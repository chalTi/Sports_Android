package com.wentongwang.mysports.views.activity.signup;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.wentongwang.mysports.constant.IntentConstants;
import com.wentongwang.mysports.utils.ToastUtil;
import com.wentongwang.mysports.views.BaseActivity;
import com.wentongwang.mysports.views.activity.home.HomeActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Wentong WANG on 2016/9/8.
 */
public class SignUpActivity extends BaseActivity implements SignUpView {


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
        ButterKnife.bind(SignUpActivity.this);
        mPresenter.init(SignUpActivity.this);
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
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, IntentConstants.EXTRA_CAMERA_PICK);
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动自带的相册选择照片
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IntentConstants.EXTRA_PICTURE_PICK);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (null != data) {
                switch (requestCode) {
                    case IntentConstants.EXTRA_CAMERA_PICK:
                        //TODO: android 6.0的拍照套路好像不一样（好像要请求权限）
                    case IntentConstants.EXTRA_PICTURE_PICK:
                        Uri uri = data.getData();
                        Intent intent = buildCropImageIntent(500, 500);
                        intent.setDataAndType(uri, "image/*");
                        startActivityForResult(intent, IntentConstants.EXTRA_CROP_PHOTO);
                        break;
                    case IntentConstants.EXTRA_CROP_PHOTO:
                        Bitmap photo = null;
                        Uri photoUrl = data.getData();
                        if (photoUrl != null) {
                            photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUrl);
                        } else {
                            Bundle extra = data.getExtras();
                            if (extra != null) {
                                photo = (Bitmap) extra.get("data");
                            }
                        }
                        userHead.setImageBitmap(photo);
                        //TODO: 将图片压缩到file里并且将这个File上传到服务器（presenter里走逻辑）
//                        File file = new File(this.getCacheDir(), "userHead");
//                        if (file.exists()) {
//                            file.delete();
//                        }
                        break;
                }
            } else {
                ToastUtil.show(this, "添加照片失败", 1000);
            }
//            multi pick 代码
//             ClipData clipData = data.getClipData();
//                    for (int i = 0, l = clipData.getItemCount(); i < l; i++) {
//                        ClipData.Item item = clipData.getItemAt(i);
//                        Uri uri = item.getUri();
//                        photos.add(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri));
//                        File file = new File(getActivity().getCacheDir(),
//                                AuthPreference.getUserId() + "momentPhoto" + System.currentTimeMillis() + "-" + photoCount);
//                        if (file.exists()) {
//                            file.delete();
//                        }
//                        photoFiles.add(file);
//                        photoCount++;
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtil.show(this, "添加照片失败", 1000);
        }
    }

    /**
     * 图片的裁剪
     *
     * @param outputX 图片输出大小
     * @param outputY 图片输出大小
     */
    private Intent buildCropImageIntent(int outputX, int outputY) {
        //裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.putExtra("crop", "true");
        //裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        //图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        return intent;
    }
}
