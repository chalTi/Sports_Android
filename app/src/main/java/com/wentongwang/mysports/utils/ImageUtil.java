package com.wentongwang.mysports.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;

/**
 * Created by Wentong WANG on 2016/8/18.
 */
public class ImageUtil {

    /**
     * 由资源id获取图片
     */
    public static Drawable getDrawableById(Context context, int resId) {
        if (context == null) {
            return null;
        }
        return context.getResources().getDrawable(resId);
    }

    /**
     *
     * 由资源id获取位图
     */
    public static Bitmap getBitmapById(Context context, int resId) {
        if (context == null) {
            return null;
        }
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    /**
     * 将Bitmap转化为字节数组
     */
    public static byte[] bitmap2byte(Bitmap bitmap) {
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] array = baos.toByteArray();
            baos.flush();
            baos.close();
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * 将byte数组转化为bitmap
     */
    public static Bitmap byte2bitmap(byte[] data) {
        if (null == data) {
            return null;
        }
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     *
     * 将Drawable转化为Bitmap
     */
    public static Bitmap drawable2bitmap(Drawable drawable) {
        if (null == drawable) {
            return null;
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);// 重点
        return bitmap;

    }

    /**
     *
     * 将bitmap转化为drawable
     */
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }

    /**
     * 将bitmap裁剪成指定大小, 一般用于在ImageView中显示 只裁剪,不压缩
     *
     * @param reqWidth
     *            裁剪后的宽度, 单位像素
     * @param reqHeight
     *            裁剪后的高度, 单位像素
     */
    public static Bitmap adjustBitMapTo(Bitmap bm, int reqWidth, int reqHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        if (width * reqHeight > height * reqWidth) {
            return Bitmap.createBitmap(bm, ((width - ((height * reqWidth) / reqHeight)) / 2), 0, (height * reqWidth)
                    / reqHeight, height);
        } else {
            return Bitmap.createBitmap(bm, 0, ((height - ((width * reqHeight) / reqWidth)) / 2), width,
                    (width * reqHeight) / reqWidth);
        }
    }

    /**
     * 创建图片的缩略图, 只截取, 不压缩
     *
     * @param bitMap
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap createBitmapThumbnail(Bitmap bitMap, int reqWidth, int reqHeight) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = reqWidth;
        int newHeight = reqHeight;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height, matrix, true);
        return newBitMap;
    }

    /**
     * 将Bitmap裁剪成圆的
     *
     * @param source
     * @param min
     * @return
     */
    public static Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 计算压缩比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 对图片进行压缩, 展示在ImageView中
     *
     * @param res
     * @param resId
     *            資源id
     * @param reqWidth
     *            要求的宽度, 单位像素
     * @param reqHeight
     *            要求的高度, 单位像素
     * @return 压缩后的bitmap
     */
    public static Bitmap decodeBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 对图片进行压缩
     *
     * @param fileName
     * 			     图片的文件名
     * @param reqWidth
     *            要求的宽度, 单位像素
     * @param reqHeight
     *            要求的高度, 单位像素
     * @return
     */
    public static Bitmap decodeBitmapFromFile(String fileName, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileName, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(fileName, options);
    }

    /**
     * 对图片进行压缩
     *
     * @param b
     * 	                             字节数组
     * @param reqWidth
     *            要求的宽度, 单位像素
     * @param reqHeight
     *            要求的高度, 单位像素
     * @return
     */
    public static Bitmap decodeBitmaoFromByteArray(byte[] b, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(b, 0, b.length, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(b, 0, b.length, options);
    }

    /**
     * 对图片进行压缩（不改变宽高）, 展示在ImageView中
     * @param reqWidth  要求的宽度, 单位像素
     * @param reqHeight 要求的高度, 单位像素
     * @return 压缩后的bitmap
     */
    public static Bitmap decodeBitmapFromFileDescriptor(FileDescriptor fileDescriptor,
                                                        int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true,来获取图片大小,不会占据内存
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    /**
     * 从文件路径中获取图片
     * @param path
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeBitmapFromPath(String path, int reqWidth, int reqHeight) {
        //获取图片宽高，但不加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        //使用获得到的samplesize
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

    /**
     * 改变图片的宽高
     * @param bm 所要转换的bitmap
     * @param newWidth 新的宽
     * @param newHeight 新的高
     * @return 指定宽高的bitmap
     */
    public static Bitmap resize(Bitmap bm, int newWidth ,int newHeight) {
        Bitmap newbm = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            newbm = ThumbnailUtils.extractThumbnail(bm, newWidth, newHeight);
        } else {
            // 获得图片的宽高
            int width = bm.getWidth();
            int height = bm.getHeight();
            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleHeight);
            // 得到新的图片
            newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        }
        return newbm;
    }
}
