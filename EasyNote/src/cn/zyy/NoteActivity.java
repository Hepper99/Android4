package cn.zyy;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NoteActivity extends FragmentActivity{
	static final int INSERTITEM = 1; 
	private ViewPager mViewPager;
	private PagerAdapter pagerAdapter;
	private TextView mTextView;
	public static ArrayList<NoteFragment> noteFragmentList; 
	public static ArrayList<Integer> noteDateList;
	public static NoteFragment currentFragment;
	public static int currentDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);	
		
		noteFragmentList = new ArrayList<NoteFragment>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd"); 
		java.util.Date date = new java.util.Date();
		int currDate = Integer.parseInt(sdf.format(date));
		currentDate = currDate;
		int beforeDate = currentDate - 1;
		int nextDate = currentDate + 1;
		currentFragment = new NoteFragment(currentDate);
		noteFragmentList.add(new NoteFragment(beforeDate));
		noteFragmentList.add(currentFragment);
		noteFragmentList.add(new NoteFragment(nextDate));		
		
		
		noteDateList = new ArrayList<Integer>();
		for(int i=0;i<200;i++)
			noteDateList.add(currentDate - 100 + i);

		mTextView = (TextView)findViewById(R.id.tvdate);
		mTextView.setText(""+currentDate);
		
		mViewPager = (ViewPager) this.findViewById(R.id.viewpager);
		pagerAdapter = new PagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(pagerAdapter);
		mViewPager.setCurrentItem(100);
		
		Button addButton = (Button)findViewById(R.id.addbutton);
		addButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NoteActivity.this, ItemActivity.class);
				startActivityForResult(intent, INSERTITEM);
			}
		});
		
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {				
			}

			@Override
			public void onPageSelected(int index) {
				if(index < 100)
				{
//					currentDate = noteDateList.getFirst();
//					noteDateList.addFirst(currentDate - 1);
//					currentFragment = noteFragmentList.getFirst();
//					noteFragmentList.addFirst(new NoteFragment(currentDate - 1));
//					pagerAdapter.notifyDataSetChanged();
					currentDate -= 1;
					Log.d("zyy","11111111111");
				}
				if(index > 2)
				{
//					currentDate = noteDateList.getLast();
//					noteDateList.addLast(currentDate + 1);
//					currentFragment = noteFragmentList.getLast();
//					noteFragmentList.addLast(new NoteFragment(currentDate + 1));
//					pagerAdapter.notifyDataSetChanged();
					currentDate += 1;
					Log.d("zyy","22222222222");
				}
				currentDate = noteDateList.get(index);
				mTextView.setText(""+currentDate);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case INSERTITEM:
			if(resultCode == Activity.RESULT_OK)
//				pagerAdapter.notifyDataSetChanged();
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
	    return true;
	}
}
