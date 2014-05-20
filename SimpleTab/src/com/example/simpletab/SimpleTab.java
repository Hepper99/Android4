package com.example.simpletab;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.drm.DrmStore.Action;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.widget.TabHost.TabContentFactory;

public class SimpleTab extends FragmentActivity implements TabListener{

	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mViewPager = (ViewPager) this.findViewById(R.id.pager);
		final ActionBar actionBar = getActionBar();
		mTabsAdapter = new TabsAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mTabsAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		 
		 actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//		 actionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		 actionBar.addTab(actionBar.newTab().setText("Page One").setTabListener(this));
		 actionBar.addTab(actionBar.newTab().setText("Page Two").setTabListener(this));
		 actionBar.setSelectedNavigationItem(0);
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());		
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());	
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
