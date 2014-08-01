package com.trigma.imagehandler;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.widget.ImageView;
import com.teamhood.util.ImageHelper;
import com.teamhood.R;

public class ImageloaderSecond {
    
    MemoryCache memoryCache=new MemoryCache();
    FileCache fileCache;
    private Map<ImageView, String> imageViews=Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService; 
    String exceptionurl;
    ImageView exceptionimageview;
    ImageHelper yeshelp=new ImageHelper();
    int pic=0,w,h,rotation=-1;
    Bitmap finalbitmap;
    public ImageloaderSecond(Context context){
        fileCache=new FileCache(context);
        executorService=Executors.newFixedThreadPool(5);
    }
    @SuppressWarnings({ "deprecation" })
	public void  DisplayImage(String url, ImageView imageView,int w,int h,int rotation)
    {
    	this.w=w;
    	this.h=h;
    	this.rotation=rotation;
        imageViews.put(imageView, url);
        Bitmap bitmap=memoryCache.get(url);  
        exceptionurl=url;
        try {
        	
    		
    		 exceptionimageview=imageView;
    	        if(bitmap!=null)
    	        {
    	        	try{
    	        	Bitmap f=yeshelp.getRoundedShape1(bitmap);
    	        //	Bitmap f=yeshelp.addblueBorder(fd, 2);
    	        	Drawable d = new BitmapDrawable(f);  
    	        	imageView.setImageBitmap(f);
    	        	}
    	        	catch(Exception e)
    	        	{
    	        		imageView.setBackgroundResource(R.drawable.demo_image);
    	        	}
    	           
    	        }
    	        else
    	        {
    	        	try{
    	            queuePhoto(url, imageView);
    	        	}
    	        	catch(Exception e)
    	        	{
    	        		imageView.setBackgroundResource(R.drawable.demo_image);
    	        	}
    	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
        
		
       
    }
        
    private void queuePhoto(String url, ImageView imageView)
    {
        PhotoToLoad p=new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p));
    }
    
    private Bitmap getBitmap(String url) 
    {
        File f=fileCache.getFile(url);
        Bitmap b = null;
		try {
			b = decodeFile(f);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(b!=null)
        	
            return b;
        try {
            Bitmap bitmap=null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is=conn.getInputStream();
            OutputStream os = new FileOutputStream(f);
            Utils.CopyStream(is, os);
            os.close();
            bitmap = decodeFile(f);
            
            return bitmap;
        } catch (Exception ex){
           ex.printStackTrace();
           return null;
        }
    }
    private Bitmap decodeFile(File f) throws IOException{
        try {
        	BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);
            final int REQUIRED_SIZE=300;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            
            if (height_tmp > REQUIRED_SIZE || width_tmp > REQUIRED_SIZE) {

                final int halfHeight = height_tmp / 2;
                final int halfWidth = width_tmp / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / scale) > REQUIRED_SIZE
                        && (halfWidth / scale) > REQUIRED_SIZE) {
                    scale *= 2;
                }
            }
//            pic++;
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            Bitmap b=BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
            
            String root = Environment.getExternalStorageDirectory().toString();
	        File myDir = new File(root + "/saved_images");    
	        myDir.mkdirs();
	        Random generator = new Random();
	        /*int n = 10000;
	        n = generator.nextInt(n);
	        String fname = "Image-"+ n +".jpg";*/
	        String fname = "Profile_pic"+".jpg";
	        File file = new File (myDir, fname);
	        
	       
	         /*  Matrix rotator = new Matrix();
	           switch (rotation) {
	           case (Surface.ROTATION_0):
	               break;
	           case (Surface.ROTATION_90):
	               rotator.postRotate(270);
	               break;
	           case (Surface.ROTATION_180):
	               rotator.postRotate(180);
	               break;
	           case (Surface.ROTATION_270):
	               rotator.postRotate(90);
	               break;
	           }*/
	          /* Bitmap bmp_ss = null ;
	           try {
	        	    bmp_ss = Bitmap.createBitmap(b, w, h, w, h, rotator, false);
			} catch (Exception e) {
				// TODO: handle exception
				Log.d("exception", e+"");
			}*/
	         
	        if (file.exists ())
	        	{
	        	file.delete (); 
	        	}
	        try {
	               FileOutputStream out = new FileOutputStream(file);
	               b.compress(Bitmap.CompressFormat.JPEG, 100, out);
	               
	               out.flush();
	               out.close();

	        } catch (Exception e) {
	               e.printStackTrace();
	               
	        }

            return b;
        } catch (FileNotFoundException e) {
        	
        	
        }
        return null;
    }
    
    private class PhotoToLoad
    {
        public String url;
        public ImageView imageView;
        public PhotoToLoad(String u, ImageView i){
            url=u; 
            imageView=i;
        }
    }
    
    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;
        PhotosLoader(PhotoToLoad photoToLoad){
            this.photoToLoad=photoToLoad;
        }
        
        @Override
        public void run() {
            if(imageViewReused(photoToLoad))
                return;
            Bitmap bmp=getBitmap(photoToLoad.url);
            memoryCache.put(photoToLoad.url, bmp);
            if(imageViewReused(photoToLoad))
                return;
            BitmapDisplayer bd=new BitmapDisplayer(bmp, photoToLoad);
            Activity a=(Activity)photoToLoad.imageView.getContext();
            a.runOnUiThread(bd);
        }
    }
    
    boolean imageViewReused(PhotoToLoad photoToLoad){
        String tag=imageViews.get(photoToLoad.imageView);
        if(tag==null || !tag.equals(photoToLoad.url))
            return true;
        return false;
    }
    
    class BitmapDisplayer implements Runnable
    {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;
        public BitmapDisplayer(Bitmap b, PhotoToLoad p){bitmap=b;photoToLoad=p;}
        public void run()
        {
            if(imageViewReused(photoToLoad))
                return;
            if(bitmap!=null){
            	 String root = Environment.getExternalStorageDirectory().toString();
 		        File myDir = new File(root + "/saved_images");    
 		       
 		       
 		        String fname = "Profile_pic"+".jpg";
            	decodeFile(myDir+"/"+fname);
          	Bitmap f1=yeshelp.getRoundedShape(finalbitmap,w,h);
          
          //	Bitmap f1=yeshelp.addblueBorder(fd1, 2);
                photoToLoad.imageView.setImageBitmap(f1);
            }
        }
    }

    public  void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }
    public void decodeFile(String filePath) {
		// Decode image size
		try {
			ExifInterface exif = new ExifInterface(filePath);
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);

			int angle = 0;

			if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
				angle = 90;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
				angle = 180;
			} else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
				angle = 270;
			}

			final	Matrix mat = new Matrix();
			mat.postRotate(angle);

			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filePath, o);

			// The new size we want to scale to
			

			// Find the correct scale value. It should be the power of 2.
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			while (true) {
				if (width_tmp < w*2 && height_tmp < h*2)
					break;
				width_tmp /= 2;
				height_tmp /= 2;
				scale *= 2;
			}

			// Decode with inSampleSize
			int scale1 = 1;
			Bitmap check=null;
			try{
				int www=(int) (w/1.5);
				int hhh=(int) (h/3.5);
				// Find the correct scale value. It should be the power of 2.
				int width_tmp1 = o.outWidth, height_tmp1 = o.outHeight;
				while (true) {
					if (width_tmp1 < www*2 && height_tmp1 < hhh*2)
					{
						break;
					}
					/*else if(width_tmp1>1700 || height_tmp1>2000)
					{
						width_tmp1 /= 5;
						height_tmp1 /= 5;
						scale1 *= 5;	
					}*/
					else{
					width_tmp1 /= 2;
					height_tmp1 /= 2;
					scale1 *= 2;
					}
				}
			}
			catch(Exception e)
			{
				
			}
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale1;

			final BitmapFactory.Options o3 = new BitmapFactory.Options();
			o3.inSampleSize = scale;
			try {

				finalbitmap = BitmapFactory.decodeFile(filePath, o2);
				finalbitmap = Bitmap.createBitmap(finalbitmap, 0, 0, finalbitmap.getWidth(),
						finalbitmap.getHeight(), mat, true);
				
			} catch (Exception e) {
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}

	}
}