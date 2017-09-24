package com.foxleezh.basecomponent.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by foxleezh on 2017/9/24.
 * 本类介绍 将Glide加载的图片转换为圆角
 */
public class GlideRoundTransform extends BitmapTransformation {

    /** 圆角大小*/
    private float radius = 0f;
    Matrix matrix=new Matrix();


    public GlideRoundTransform(Context context) {
        this(context, 7);
    }


    public GlideRoundTransform(Context context, int dp) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
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
        RectF rect=new RectF(1,1,out-1,out-1);
        canvas.drawRoundRect(rect, radius, radius, paint);
        squared.recycle();
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }

}
