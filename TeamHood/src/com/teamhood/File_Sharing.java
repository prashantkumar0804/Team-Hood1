package com.teamhood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class File_Sharing extends Activity implements OnClickListener{

	ImageView file_sharing_back,file_sharing_search,file_sharing_upload;
	LinearLayout file_sharing_all_files_lay,file_sharing_your_files_lay,file_sharing_image_lay,file_sharing_pdf_lay,file_sharing_google_doc_lay;
	TextView file_sharing_header,file_sharing_all_files,file_sharing_your_files,file_sharing_image,file_sharing_pdf,file_sharing_google_doc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.file_sharing);
		
		
		Typeface font = Typeface.createFromAsset(getAssets(), "NesobriteLt-Regular.ttf");
		Typeface font2 = Typeface.createFromAsset(getAssets(), "NesobriteRg-Bold.ttf");
		
		file_sharing_header=(TextView)findViewById(R.id.file_sharing_header);
		file_sharing_header.setTypeface(font2);
		file_sharing_back=(ImageView)findViewById(R.id.file_sharing_back);
		file_sharing_back.setOnClickListener(this);
		file_sharing_search=(ImageView)findViewById(R.id.file_sharing_search);
		file_sharing_upload=(ImageView)findViewById(R.id.file_sharing_upload);
		file_sharing_all_files_lay=(LinearLayout)findViewById(R.id.file_sharing_all_files_lay);
		file_sharing_all_files_lay.setOnClickListener(this);
		file_sharing_all_files=(TextView)findViewById(R.id.file_sharing_all_files);
		file_sharing_all_files.setTypeface(font2);
		file_sharing_your_files_lay=(LinearLayout)findViewById(R.id.file_sharing_your_files_lay);
		file_sharing_your_files=(TextView)findViewById(R.id.file_sharing_your_files);
		file_sharing_your_files.setTypeface(font2);
		file_sharing_image_lay=(LinearLayout)findViewById(R.id.file_sharing_image_lay);
		
		file_sharing_image=(TextView)findViewById(R.id.file_sharing_image);
		file_sharing_image.setTypeface(font2);
		file_sharing_pdf_lay=(LinearLayout)findViewById(R.id.file_sharing_pdf_lay);
		file_sharing_pdf=(TextView)findViewById(R.id.file_sharing_pdf);
		file_sharing_pdf.setTypeface(font2);
		file_sharing_google_doc_lay=(LinearLayout)findViewById(R.id.file_sharing_google_doc_lay);
		file_sharing_google_doc=(TextView)findViewById(R.id.file_sharing_google_doc);
		file_sharing_google_doc.setTypeface(font2);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.file_sharing_all_files_lay:
			Intent intent2=new Intent(File_Sharing.this,All_Files_Screen.class);
			
			startActivity(intent2);
			overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
			finish();
			break;
			
		case R.id.file_sharing_back:
			Intent intent=new Intent(File_Sharing.this,Dash_Board.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
			finish();
			break;

		default:
			break;
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent=new Intent(File_Sharing.this,Dash_Board.class);
		startActivity(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right );
		finish();
	}
	

}
