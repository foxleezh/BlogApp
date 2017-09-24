package com.foxleezh.basecomponent.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 将Glide加载的图片转换为圆形
 */
public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }
    Matrix matrix=new Matrix();
    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if (toTransform == null) return null;
        Bitmap result=pool.get(outWidth,outHeight, Bitmap.Config.ARGB_8888);
        int size = Math.min(toTransform.getWidth(), toTransform.getHeight());
        int x = (toTransform.getWidth() - size) / 2;
        int y = (toTransform.getHeight() - size) / 2;
        float out=outWidth;
        float scale=out/size;
        matrix.reset();
        matrix.postScale(scale,scale);
        Bitmap squared = Bitmap.createBitmap(toTransform, x, y, size, size,matrix,true);
        if(result==null) {
            result = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = out / 2f;
        canvas.drawCircle(r, r, r-1, paint);
        squared.recycle();
        return result;
    }

    @Override public String getId() {
        return getClass().getName();
    }
}
