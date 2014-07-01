package com.example.sliderclass;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class CollapseAnimation extends TranslateAnimation implements TranslateAnimation.AnimationListener{
	
	private LinearLayout slidingLayout;
	private RelativeLayout menuPanel;
	int panelWidth;

	public CollapseAnimation(LinearLayout layout, int width, int fromXType, float fromXValue, int toXType,
			float toXValue, int fromYType, float fromYValue, int toYType, float toYValue,RelativeLayout layout1) {
		
		super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
		
		//Initialize
		try {
			slidingLayout = layout;
			menuPanel=layout1;
	  	    panelWidth = width;
			setDuration(400);
		    setFillAfter( false );
		    setInterpolator(new AccelerateDecelerateInterpolator());
		    setAnimationListener(this);
		    
		    //Clear left and right margins
		    LayoutParams params = (LayoutParams) slidingLayout.getLayoutParams();
	  	   	params.rightMargin = 0;
	  	   	params.leftMargin = 0;
	  	   	slidingLayout.setLayoutParams(params);
	  	   	slidingLayout.requestLayout();       
	  	   	slidingLayout.startAnimation(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
  	   	 
	}
	public void onAnimationEnd(Animation animation) {
		try {
			menuPanel.setVisibility(View.GONE);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

	public void onAnimationRepeat(Animation animation) {
		
	}

	public void onAnimationStart(Animation animation) {
		
	}
	
}