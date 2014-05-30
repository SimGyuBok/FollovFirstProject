package com.follov.calendar;

import java.util.*;
import java.util.Calendar;

import com.follov.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.*;
import android.graphics.Color;
import android.os.Bundle;
import android.util.*;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;

public class CalendarActivity extends Activity 
{
	public static final String LOG_TAG = CalendarActivity.class.getSimpleName();
	private ArrayList<TextView> dayListView;
	private ArrayList<TextView> sNumListView;
	private TextView todayView;
	private int iYear;
	private int iMonth;
	private int iDay;
	private int nowYear;
	private int nowMonth;
	private int nowDay;
//	private ImageView btnShowScheduleImageView;
//	private ImageView btnAddScheduleImageView;
	private int currentCheckDay = 0;

	@Override
	protected void onResume() 
	{
		super.onResume();
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.calendar_view);
		Calendar nowcalendar = Calendar.getInstance();
		
		iYear = nowcalendar.get(Calendar.YEAR);
		iMonth = nowcalendar.get(Calendar.MONTH);
		iDay = nowcalendar.get(Calendar.DATE);

		nowYear = nowcalendar.get(Calendar.YEAR);
		nowMonth = nowcalendar.get(Calendar.MONTH);
		nowDay = nowcalendar.get(Calendar.DATE);
		
		todayView = (TextView) findViewById(R.id.today);
		dayListView = new ArrayList<TextView>();
		sNumListView = new ArrayList<TextView>();

		TableLayout monthTablelayout = (TableLayout) findViewById(R.id.month_table);
		for (int i = 0; i < 6; i++)
		{
			TableRow weekTableRow = new TableRow(this);
			for (int j = 0; j < 7; j++)
			{
				TextView dayText = new TextView(this);
				TextView sNumText = new TextView(this);
				LinearLayout dateLayout = new LinearLayout(this);
				dateLayout.setGravity(Gravity.CENTER_HORIZONTAL);
				dateLayout.setPadding(0, 2, 0, 2);
				dateLayout.setOrientation(1);
				dayText.setLayoutParams(new LinearLayout.LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

				dayText.setGravity(Gravity.CENTER_HORIZONTAL);
				sNumText.setGravity(Gravity.CENTER_HORIZONTAL);
				dateLayout.addView(dayText);
				dateLayout.addView(sNumText);
				weekTableRow.addView(dateLayout);
				dayListView.add(dayText);
				sNumListView.add(sNumText);
			}
			monthTablelayout.addView(weekTableRow);
		}

		monthTablelayout.setStretchAllColumns(true);

		monthTablelayout = (TableLayout) findViewById(R.id.week_table);
		monthTablelayout.setStretchAllColumns(true);

		setCalendar(iYear, iMonth);

		ImageView preBtn = (ImageView) findViewById(R.id.pre);
		preBtn.setOnClickListener(new Button.OnClickListener()
		{

			public void onClick(View v)
			{
				iMonth--;
				if (iMonth <= 0) {
					iYear--;
					iMonth = 11;
				}
				setCalendar(iYear, iMonth);

			}
		});

		ImageView nextBtn = (ImageView) findViewById(R.id.next);
		nextBtn.setOnClickListener(new Button.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				iMonth++;
				if (iMonth >= 12) {
					iMonth = 0;
					iYear++;
				}
				setCalendar(iYear, iMonth);
			}
		});
	}

	private void setCalendar(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		todayView.setText(year + "년 " + (month + 1) + "월");
		todayView.setTextSize(18);
		todayView.setBackgroundResource(R.drawable.boarder_clickable);

		todayView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
				final LinearLayout linear = (LinearLayout)View.inflate(CalendarActivity.this, R.layout.schedule_date_select, null);
				final DatePicker dp;
				dp = (DatePicker)linear.findViewById(R.id.maindatePicker);

				dp.init(iYear, iMonth, iDay, new DatePicker.OnDateChangedListener() {

					public void onDateChanged(DatePicker arg0, int arg1, int arg2, int arg3) {
						iYear=arg1;
						iMonth=arg2;
						iDay=arg3;
					}
				});

				new AlertDialog.Builder(CalendarActivity.this)
				.setTitle("날짜 설정")
				.setIcon(R.drawable.time_choice)
				.setView(linear)
				.setPositiveButton("확인", new DialogInterface.OnClickListener()
				{
					public void onClick(DialogInterface arg0, int arg1)
					{
						setCalendar(iYear, iMonth);
					}
				})
				.setNegativeButton("취소", null)
				.show();
			}
		});
		int whatDay = calendar.get(Calendar.DAY_OF_WEEK);
		int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
				+ whatDay - 1;
		int j = 1;

		for (int i = 0; i < dayListView.size(); i++)
		{
			((LinearLayout)dayListView.get(i).getParent()).setOnTouchListener(null);
			dayListView.get(i).setTextSize(18);
			dayListView.get(i).setText(" ");
			((LinearLayout)dayListView.get(i).getParent()).setBackgroundColor(Color.WHITE);
			sNumListView.get(i).setTextSize(12);
			sNumListView.get(i).setText(" ");
		}

		for (int i = whatDay - 1; i < lastday; i++)
		{
			dayListView.get(i).setTextColor(Color.BLACK);
			sNumListView.get(i).setTextColor(Color.BLACK);
			
			if(checkSeocka(year, month+1, j)) {
				dayListView.get(i).setTextColor(Color.RED);
			}
			
			if(year==nowYear&&month==nowMonth&&j==nowDay)
			{
				currentCheckDay = i;
				((LinearLayout)dayListView.get(i).getParent()).setBackgroundColor(Color.rgb(0xc6,0xff,0xff));
			}
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
			{
				dayListView.get(i).setTextColor(Color.RED);
			} 
			else if (calendar.get(Calendar.DAY_OF_WEEK) == 7)
			{
				dayListView.get(i).setTextColor(Color.BLUE);
			}

			switch (month + 1)
			{
			case 1:
				if (j == 1)
				{
					dayListView.get(i).setTextColor(Color.RED);
				}
				break;
			case 2:
				break;
			case 3:
				if (j == 1)
				{
					dayListView.get(i).setTextColor(Color.RED);
				}
				break;
			case 4:
				break;
			case 5:
				if (j == 5)
				{
					dayListView.get(i).setTextColor(Color.RED);
				}
				break;
			case 6:
				if (j == 6)
				{
					dayListView.get(i).setTextColor(Color.RED);
				}
				break;
			case 7:
				break;
			case 8:
				if (j == 15)
				{
					dayListView.get(i).setTextColor(Color.RED);
				}
				break;
			case 9:
				break;
			case 10:
				if (j == 3)
				{
					dayListView.get(i).setTextColor(Color.RED);
				}
				break;
			case 11:
				break;
			case 12:
				if (j == 25)
				{
					dayListView.get(i).setTextColor(Color.RED);
				}
				break;
			}


			final int ii = i;
			final int y = year;
			final int m = month + 1;
			final int d = j;



			View.OnTouchListener listOn = new OnTouchListener()
			{
				public boolean onTouch(View v, MotionEvent e)
				{
					int distance = 0;
					int downX = 0; 
					if (e.getAction() == MotionEvent.ACTION_DOWN)
					{
						downX = (int)e.getX();
					}
					else if (e.getAction() == MotionEvent.ACTION_UP)
					{
						int currentX = (int)e.getX();
						distance = currentX-downX;
						if(distance<-100){
							iMonth++;
							if (iMonth >= 12) {
								iMonth = 0;
								iYear++;
							}
							setCalendar(iYear, iMonth);
						}else if(distance>100){
							iMonth--;
							if (iMonth <= 0) {
								iYear--;
								iMonth = 11;
							}
							setCalendar(iYear, iMonth);
						}
						else{								
							if ((int) e.getX() < 0
									|| (int) e.getX() > ((LinearLayout) dayListView
											.get(ii).getParent())
											.getWidth()
											|| (int) e.getY() < 0
											|| (int) e.getY() > ((LinearLayout) dayListView
													.get(ii).getParent())
													.getHeight())
							{

							}
							else
							{
								((LinearLayout)dayListView.get(currentCheckDay).getParent()).setBackgroundColor(Color.WHITE);
								((LinearLayout)dayListView.get(ii).getParent()).setBackgroundColor(Color.rgb(0xc6,0xff,0xff));
								currentCheckDay=ii;

								nowYear = y;
								nowMonth = m-1;
								nowDay = d;

								iDay=d;
							}
						}
					}
					return true;
				}
			};

			((LinearLayout) dayListView.get(i).getParent()).setOnTouchListener(listOn);


			dayListView.get(i).setTextSize(18);
			dayListView.get(i).setText(j++ + "");
			sNumListView.get(i).setTextSize(12);

			calendar.set(year, month, j);
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	}

	public void scheduleTableSetting() 
	{
		LinearLayout scheduleTable = (LinearLayout) findViewById(R.id.shcedule_table);
		scheduleTable.removeAllViews();
	}

	protected void onDestroy()
	{
		super.onDestroy();
	}	

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = null;
		String popupText = getString(id);
		builder = new AlertDialog.Builder(this);
		builder.setMessage(popupText);
		builder.setNegativeButton("확인", null);
		//if(builder == null){
		//	return super.onCreateDialog(id);
		//}
		return builder.create();
	}	
	
	private boolean checkSeocka(int year, int month, int day) {
/*
		ChineseCalendar cc = new ChineseCalendar();
//		DateTime dt = new DateTime();
		cc.set(year, month, day);
		
		int cYear = cc.get(ChineseCalendar.YEAR);
		int cMonth = cc.get(ChineseCalendar.MONTH);
		int cDay = cc.get(ChineseCalendar.DAY_OF_YEAR);
		
		Log.d("CC", "cYear: "+cYear+", cMonth: "+cMonth+", day: "+cDay);
		
		if (cMonth == 4 && cDay == 8) {
			return true;
		} else {
			return false;
		}
*/		
		StringBuilder sb = new StringBuilder();
		sb.append(Integer.toString(year));
		if (month < 10) {
			sb.append(0);			
		}
		sb.append(Integer.toString(month));
		if (day < 10) {
			sb.append(0);		
		}
		sb.append(Integer.toString(day));
		String yyyymmdd = sb.toString();
		
//		Log.d("CC", yyyymmdd);
		
		LunarCalendar lc = new LunarCalendar();
		String yyyymmddres = lc.toLunar(yyyymmdd);
		
//		Log.d("CC", "res: "+ yyyymmddres + "length: "+yyyymmddres.length());
		int cMonth = Integer.parseInt(yyyymmddres.substring(4, 6));
		int cDay = Integer.parseInt(yyyymmddres.substring(6));
		Log.d("CC", "cMonth: "+cMonth + ", cDay: "+cDay);
		
		if (cMonth == 4 && cDay == 8) {
			return true;
		} else {
			return false;
		}
	}
}
