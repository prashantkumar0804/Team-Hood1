package com.teamhood;



import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";
    

    public static TestFragment newInstance(Context ctx,String content, int icons) {
    	
    	TestFragment fragment = new TestFragment();

    	fragment.ctx=ctx;
        fragment.mContent = content;
        
       
        fragment.mContent1 = icons;

        return fragment;
    }

    private String mContent = "???";
    private int mContent1 ;
    private Context ctx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       /* TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText(mContent);
        text.setTextSize(20 * getResources().getDisplayMetrics().density);
        text.setPadding(20, 20, 20, 20);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(text);*/
    	Typeface font = Typeface.createFromAsset(ctx.getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(ctx.getAssets(), "NesobriteRg-Bold.ttf");

    	View view = inflater.inflate(R.layout.pager_item, container, false);
		ImageView imageView = (ImageView) view.findViewById(R.id.pager_image);
		
		TextView pager_text = (TextView) view.findViewById(R.id.dashboard_welcome_text);
		pager_text.setTypeface(font);
		imageView.setImageResource(mContent1);
		
		
		pager_text.setText(mContent);
		Log.e("Test3", mContent1+""+"/"+mContent);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
}
