package com.example.sliderclass;


import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;

public class ExpandAnimation1 extends TranslateAnimation implements Animation.AnimationListener{

	private LinearLayout slidingLayout;
	int panelWidth;
	
	public ExpandAnimation1(LinearLayout layout, int width, int fromXType, float fromXValue, int toXType,
			float toXValue, int fromYType, float fromYValue, int toYType, float toYValue) {
		
		super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
		try {
			//Initialize   
			slidingLayout = layout;
			panelWidth = width;
			setDuration(400);
	  	    setFillAfter( false );   
	  	    setInterpolator(new AccelerateDecelerateInterpolator());
	  	    setAnimationListener(this);
	  	    slidingLayout.startAnimation(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}


	public void onAnimationEnd(Animation arg0) {
		try {
			//Create margin and align left
			LayoutParams params = (LayoutParams) slidingLayout.getLayoutParams();
	  	   	params.rightMargin = panelWidth;
	  	   	params.gravity = Gravity.RIGHT;	   
	  	   	slidingLayout.clearAnimation();
	  	   	slidingLayout.setLayoutParams(params);
	  	   	slidingLayout.requestLayout();
		} catch (Exception e) {
			// TODO: handle exception
		}
  	  			
	}

	public void onAnimationRepeat(Animation arg0) {
		
	}

	public void onAnimationStart(Animation arg0) {
		
	}

}