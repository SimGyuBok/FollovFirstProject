package com.follov.ui;

import com.follov.*;
import com.follov.R.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
	public static final int HOME_TAB = 1;
	public static final int COUPLENEWS_TAB = 2;
	public static final int CLOUD_TAB = 3;
	public View homeActivity, couplenewsActivity, cloudActivity;
	public ImageButton homeTabBtn, coupleNewsTabBtn, cloudTabBtn;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		homeActivity = findViewById(R.id.home_activity);
		couplenewsActivity = findViewById(R.id.couplenews_activity);
		cloudActivity = findViewById(R.id.cloud_activity);
		homeTabBtn = (ImageButton)findViewById(R.id.hometab_btn);
		coupleNewsTabBtn = (ImageButton)findViewById(R.id.couplenewstab_btn);
		cloudTabBtn = (ImageButton)findViewById(R.id.cloudtab_btn);
		
		homeTabBtn.setOnClickListener(mClickListener);
		coupleNewsTabBtn.setOnClickListener(mClickListener);
		cloudTabBtn.setOnClickListener(mClickListener);
	}

	private Button.OnClickListener mClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.hometab_btn:
				changePage(HOME_TAB);
				break;
			case R.id.couplenewstab_btn:
				changePage(COUPLENEWS_TAB);
				break;
			case R.id.cloudtab_btn:
				changePage(CLOUD_TAB);
				break;
			}
		}
	};
	
	void changePage(int page) {
		homeActivity.setVisibility(View.INVISIBLE);
		couplenewsActivity.setVisibility(View.INVISIBLE);
		cloudActivity.setVisibility(View.INVISIBLE);
		homeTabBtn.setImageResource(R.drawable.hometab_img_off);
		coupleNewsTabBtn.setImageResource(R.drawable.couplenewstab_img_off);
		cloudTabBtn.setImageResource(R.drawable.cloudtab_img_off);
		
		switch (page) {
		case HOME_TAB :
			homeActivity.setVisibility(View.VISIBLE);
			homeTabBtn.setImageResource(R.drawable.hometab_img_on);
			break;
		case COUPLENEWS_TAB :
			couplenewsActivity.setVisibility(View.VISIBLE);
			coupleNewsTabBtn.setImageResource(R.drawable.couplenewstab_img_on);
			break;
		case CLOUD_TAB :
			cloudActivity.setVisibility(View.VISIBLE);
			cloudTabBtn.setImageResource(R.drawable.cloudtab_img_on);
			break;
		}
	}
}
