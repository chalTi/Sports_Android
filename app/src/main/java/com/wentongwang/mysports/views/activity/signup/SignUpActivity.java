package com.wentongwang.mysports.views.activity.signup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.wentongwang.mysports.base.BaseActivity;
import com.wentongwang.mysports.views.activity.home.HomeActivity;

import java.io.File;
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
        ButterKnife.bind(SignUpActivity.this);
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

    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    IntentConstants.MY_PERMISSIONS_REQUEST_TAKE_PHOTO);

        } else {
            goToTakePhoto();
        }
    }

    private void choosePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    IntentConstants.MY_PERMISSIONS_REQUEST_CHOOSE_PHOTO);

        } else {
            //启动自带的相册选择照片
            goToChoosePhoto();
        }
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
            switch (requestCode) {
                case IntentConstants.EXTRA_CAMERA_PICK:
                    Intent intent1 = buildCropImageIntent(500, 500);
                    intent1.setDataAndType(imageUri, "image/*");
                    intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent1, IntentConstants.EXTRA_CROP_PHOTO);
                    break;
                case IntentConstants.EXTRA_PICTURE_PICK:
                    Uri uri = data.getData();
                    Intent intent = buildCropImageIntent(500, 500);
                    intent.setDataAndType(uri, "image/*");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, IntentConstants.EXTRA_CROP_PHOTO);
                    break;
                case IntentConstants.EXTRA_CROP_PHOTO:
                    Bitmap photo = null;
                    photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    userHead.setImageBitmap(photo);
                    //TODO: 将图片压缩到file里并且将这个File上传到服务器（presenter里走逻辑）
//                        File file = new File(this.getCacheDir(), "userHead");
//                        if (file.exists()) {
//                            file.delete();
//                        }
                    break;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == IntentConstants.MY_PERMISSIONS_REQUEST_TAKE_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goToTakePhoto();
            } else {
                // Permission Denied
                ToastUtil.show(this, "权限获取失败", 1000);
            }
        }
        if (requestCode == IntentConstants.MY_PERMISSIONS_REQUEST_CHOOSE_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                goToChoosePhoto();
            } else {
                // Permission Denied
                ToastUtil.show(this, "权限获取失败", 1000);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void goToTakePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, IntentConstants.EXTRA_CAMERA_PICK); // 启动相机程序

//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, IntentConstants.EXTRA_CAMERA_PICK);
    }

    private void goToChoosePhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IntentConstants.EXTRA_PICTURE_PICK);
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
