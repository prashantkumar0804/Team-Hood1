package com.teamhood;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.wheel.ArrayWheelAdapter;
import com.wheel.NumericWheelAdapter;
import com.wheel.OnWheelChangedListener;
import com.wheel.WheelView;

public class FooterClass extends Dialog {

	private Context Mcontex;

	private int NoOfYear = 40; 
	
	public FooterClass( final String text,final LinearLayout footer,Context context, Calendar calendar
			) {

		super(context);
		Mcontex = context;
		LinearLayout lytmain = new LinearLayout(Mcontex);
		lytmain.setOrientation(LinearLayout.VERTICAL);
		LinearLayout lytdate = new LinearLayout(Mcontex);
		
		LinearLayout lytbutton = new LinearLayout(Mcontex);

		Button btnset = new Button(Mcontex);

		btnset.setText("Done");

		final WheelView month = new WheelView(Mcontex);
		final WheelView year = new WheelView(Mcontex);
		final WheelView day = new WheelView(Mcontex);
		final WheelView hour = new WheelView(Mcontex);
		final WheelView minute = new WheelView(Mcontex);
		final WheelView prime = new WheelView(Mcontex);
		
		lytdate.addView(day, new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.26f));
		lytdate.addView(month, new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT,0.24f));
		lytdate.addView(year, new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.22f));
		
		
		lytdate.addView(hour, new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.26f));
		
		lytdate.addView(minute, new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.26f));
		
		lytdate.addView(prime, new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0.25f));
		
		
		
		lytbutton.setBackgroundColor(Color.GRAY);
		lytbutton.setLayoutParams(new LayoutParams(
				LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		lytbutton.addView(btnset);

	
		
		
		
		
		LinearLayout lytbn1 = new LinearLayout(Mcontex);
		lytbn1.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		lytbn1.addView(lytbutton);
		
		
		lytmain.addView(lytbn1);
		lytmain.addView(lytdate);
		footer.addView(lytmain);

		getWindow().setLayout(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT);
		OnWheelChangedListener listener = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				updateDays(year, month, day,hour,minute);
				updateDays(year, month, day,hour,minute,prime);

			}
		};

		// month
		int curMonth = calendar.get(Calendar.MONTH);
		String months[] = new String[] { "Jan", "Feb", "Mar",
				"Apr", "May", "Jun", "Jul", "Aug", "Sep",
				"Oct", "Nov", "Dec" };
		
		/*String months[] = new String[] { "01", "02", "03",
				"04", "05", "06", "07", "08", "09",
				"10", "11", "12" };*/
		
		month.setViewAdapter(new DateArrayAdapter(context, months, curMonth));
		month.setCurrentItem(curMonth);
		month.addChangingListener(listener);
		

		Calendar cal = Calendar.getInstance();
		// year
		int curYear = calendar.get(Calendar.YEAR);
		int Year = cal.get(Calendar.YEAR);
		year.setViewAdapter(new DateNumericAdapter(context, Year - NoOfYear,
				Year + NoOfYear, NoOfYear));  
		year.setCurrentItem(curYear-(Year-NoOfYear));
		year.addChangingListener(listener);

		// day
		
		

		int max =calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int cur_day = calendar.get(Calendar.DAY_OF_MONTH);
		String days[] =new String[max];
		for(int i=0;i<max;i++)
		{
			int f=i+1;
			days[i]=""+f;
		}
		
		day.setViewAdapter(new DateArrayAdapter(context, days, cur_day));
		day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
		day.addChangingListener(listener);
		
		
		// hour
		int cur_hour = calendar.get(Calendar.HOUR_OF_DAY);
		Log.d("here code",""+cur_hour);
		
		
		if(cur_hour==0)
		{
			String hours[] = new String[] { "01", "02", "03",
					"04", "05", "06", "07", "08", "09",
					"10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","00" };
			hour.setViewAdapter(new DateArrayAdapter(context, hours, 23));
			hour.setCurrentItem(23);
			hour.addChangingListener(listener);
		}else
		{	cur_hour=cur_hour-1;
			String hours[] = new String[] { "01", "02", "03",
					"04", "05", "06", "07", "08", "09",
					"10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","00"};
			hour.setViewAdapter(new DateArrayAdapter(context, hours, cur_hour));
			hour.setCurrentItem(calendar.get(Calendar.HOUR)-1);
			hour.addChangingListener(listener);
		}
		int cur_minute = calendar.get(Calendar.MINUTE);
		
		Log.d("here code",""+cur_minute);
		
		String minutes[] = new String[] { "01", "02", "03",
				"04", "05", "06", "07", "08", "09",
				"10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28",
				"29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47"
				,"48","49","50","51","52","53","54","55","56","57","58","59","00"};
		
		if(cur_minute==0)
		{
			minute.setViewAdapter(new DateArrayAdapter(context, minutes, 59));
			minute.setCurrentItem(59);
			minute.addChangingListener(listener);	
		}else
		{
			cur_minute=cur_minute-1;
			minute.setViewAdapter(new DateArrayAdapter(context, minutes, cur_minute));
			minute.setCurrentItem(calendar.get(Calendar.MINUTE)-1);
			minute.addChangingListener(listener);	
		}
		
		
		
		
		// am pm
		
		int cur_prime = calendar.get(Calendar.AM_PM);;
		String primes[] = new String[] {"AM","PM"};
		prime.setViewAdapter(new DateArrayAdapter(context, primes, cur_prime));
		prime.setCurrentItem(cur_prime);
		prime.addChangingListener(listener);
		updateDays(year, month, day, hour,minute,prime);
		//update
//		updateDays(year, month, day, hour,minute);
		//
		btnset.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar c = updateDays(year, month, day,hour,minute,prime);
//				Calendar c = updateDays(year, month, day,hour,minute);
				footer.setVisibility(View.GONE);
				  
			String date_here=	new SimpleDateFormat("MM dd yy hh mm aa")
				.format(c.getTime());
				
			String datehere_split[]=date_here.split(" ");
			
//			datehere_split[0]=Calendar.datehere_split[0];
			
			try{
				Create_Message.First_editText=(datehere_split[2]+"-"+datehere_split[0]+"-"+datehere_split[1]+" "+datehere_split[3]+":"+datehere_split[4]+" "+datehere_split[5]);
//				text.setText(datehere_split[2]+"-"+datehere_split[0]+"-"+datehere_split[1]+" "+datehere_split[3]+":"+datehere_split[4]);
			}
			catch(Exception e)
			{
				
			}
				//dtp.OnDoneButton(DatePickerDailog.this, c);
			}
		});
	

	}

	Calendar updateDays(WheelView year, WheelView month, WheelView day,WheelView hour,WheelView minute,WheelView prime) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				calendar.get(Calendar.YEAR) + (year.getCurrentItem()-NoOfYear));
		
		calendar.set(Calendar.MONTH, month.getCurrentItem());

		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		day.setViewAdapter(new DateNumericAdapter(Mcontex, 1, maxDays, calendar
				.get(Calendar.DAY_OF_MONTH) - 1));
		
		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		day.setCurrentItem(curDay - 1, true);
		calendar.set(Calendar.DAY_OF_MONTH, curDay);
		
		calendar.set(Calendar.HOUR_OF_DAY, hour.getCurrentItem()+1);
		
		calendar.set(Calendar.MINUTE, minute.getCurrentItem()+1);
		
		calendar.set(Calendar.AM_PM, prime.getCurrentItem());
		
		return calendar;

	}

	private class DateNumericAdapter extends NumericWheelAdapter {
		int currentItem;
		int currentValue;

		public DateNumericAdapter(Context context, int minValue, int maxValue,
				int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(20);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				view.setTextColor(0xFF0000F0);
			}
			view.setTypeface(null, Typeface.BOLD);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}

	private class DateArrayAdapter extends ArrayWheelAdapter<String> {
		int currentItem;
		int currentValue;

		public DateArrayAdapter(Context context, String[] items, int current) {
			super(context, items);
			this.currentValue = current;
			setTextSize(20);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				view.setTextColor(0xFF0000F0);
			}
			view.setTypeface(null, Typeface.BOLD);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}

	public interface DatePickerListner {
		public void OnDoneButton(Dialog datedialog, Calendar c);

		public void OnCancelButton(Dialog datedialog);
	}
}
