package com.trigma.imagehandler;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

public class ImageHelper {
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(4, 4, bitmap.getWidth()-4, bitmap.getHeight()-4);
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		canvas.drawColor(Color.parseColor("#335391"));
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	public Bitmap addblueBorder(Bitmap bmp, int borderSize) {
		Bitmap bmpWithBorder = Bitmap.createBitmap(bmp.getWidth() + borderSize
				* 2, bmp.getHeight() + borderSize * 2, bmp.getConfig());
		Canvas canvas = new Canvas(bmpWithBorder);
		canvas.drawColor(Color.parseColor("#335391"));
		canvas.drawBitmap(bmp, borderSize, borderSize, null);
		return bmpWithBorder;
	}

	public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
		// TODO Auto-generated method stub
		int targetWidth = 300;
		int targetHeight = 300;
		Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,

		targetHeight, Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(targetBitmap);
		Path path = new Path();
		path.addCircle(((float) targetWidth - 1) / 2,
				((float) targetHeight - 1) / 2,
				(Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
				Path.Direction.CCW);

		canvas.clipPath(path);
		Bitmap sourceBitmap = scaleBitmapImage;

		canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(),
				sourceBitmap.getHeight()), new Rect(0, 0, targetWidth,
				targetHeight), null);
		return targetBitmap;
	}

	public Bitmap getRoundedShape1(Bitmap bitmap) {
		int w =300;
		int h = 300;

		int radius = Math.min(h / 2, w / 2);
		Bitmap output = Bitmap.createBitmap(w + 8, h + 8, Config.ARGB_8888);

		Paint p = new Paint();
		p.setAntiAlias(true);

		Canvas c = new Canvas(output);
		c.drawARGB(0, 0, 0, 0);
		
		c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);
		
		
		p.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		c.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(),
				bitmap.getHeight()), new Rect(0, 0, w,
				h), p);
		p.setStyle(Style.STROKE);
		p.setColor(Color.parseColor("#335391"));
		p.setStrokeWidth(6);
		
		c.drawCircle((w / 2) + 4, (h / 2) + 4, radius, p);

		return output;
	}

}