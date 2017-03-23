package com.wentongwang.mysports.base;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.wentongwang.mysports.constant.IntentConstants;
import com.wentongwang.mysports.utils.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 用于照片选择的基础Activity类
 * Created by Wentong WANG on 2017/3/23.
 */
public abstract class GetPictureBaseActivity extends BaseActivity {
    private Uri imageUri;
    private File outputFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFileForPicture();
    }

    private void initFileForPicture() {
        File outputFileDir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "userHead");
        if (!outputFileDir.exists()) {
            outputFileDir.mkdirs();
        }
        try {
            outputFile = File.createTempFile("user_head", ".jpg", outputFileDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputFile);
    }

    protected void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    IntentConstants.MY_PERMISSIONS_REQUEST_TAKE_PHOTO);

        } else {
            goToTakePhoto();
        }
    }

    protected void choosePhoto() {
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

    protected void chooseMultiPhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    IntentConstants.MY_PERMISSIONS_REQUEST_CHOOSE_MULTI_PHOTO);

        } else {
            //启动自带的相册选择照片
            goToChooseMultiPhoto();
        }
    }
    /**
     * 获取需要存放图片的ImageView
     */
    protected abstract ImageView getImageView();

    /**
     * 多张选择的时候返回的图片们
     * @param bitmaps
     */
    protected abstract void imagesPicked(List<Bitmap> bitmaps);

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
                    if (getImageView() != null)
                        getImageView().setImageBitmap(photo);
                    //TODO: 将图片压缩到file里并且将这个File上传到服务器（presenter里走逻辑）
//                        File file = new File(this.getCacheDir(), "userHead");
//                        if (file.exists()) {
//                            file.delete();
//                        }
                    break;
                case IntentConstants.EXTRA_PICTURE_MULTI_PICK:
                    //            multi pick 代码
                    ClipData clipData = data.getClipData();
                    for (int i = 0, l = clipData.getItemCount(); i < l; i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        Uri imageUri = item.getUri();
                        //TODO: 用新的临时文件去存拿到的图片传给服务器
//                        photos.add(MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri));
//                        File file = new File(getActivity().getCacheDir(),
//                                AuthPreference.getUserId() + "momentPhoto" + System.currentTimeMillis() + "-" + photoCount);
//                        if (file.exists()) {
//                            file.delete();
//                        }
//                        photoFiles.add(file);
//                        photoCount++;
                        break;
                    }
            }
            }catch(IOException e){
                e.printStackTrace();
                ToastUtil.show(this, "添加照片失败", 1000);
            }
        }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults){

            switch (requestCode) {
                case IntentConstants.MY_PERMISSIONS_REQUEST_TAKE_PHOTO:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        goToTakePhoto();
                    } else {
                        // Permission Denied
                        ToastUtil.show(this, "权限获取失败", 1000);
                    }
                    break;
                case IntentConstants.MY_PERMISSIONS_REQUEST_CHOOSE_PHOTO:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        goToChoosePhoto();
                    } else {
                        // Permission Denied
                        ToastUtil.show(this, "权限获取失败", 1000);
                    }
                    break;
                case IntentConstants.MY_PERMISSIONS_REQUEST_CHOOSE_MULTI_PHOTO:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        goToChooseMultiPhoto();
                    } else {
                        // Permission Denied
                        ToastUtil.show(this, "权限获取失败", 1000);
                    }
                    break;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    private void goToTakePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, IntentConstants.EXTRA_CAMERA_PICK); // 启动相机程序
    }

    private void goToChoosePhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IntentConstants.EXTRA_PICTURE_PICK);
    }

    private void goToChooseMultiPhoto() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IntentConstants.EXTRA_PICTURE_MULTI_PICK);
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
