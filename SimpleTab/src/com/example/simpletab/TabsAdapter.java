package com.example.simpletab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



public class TabsAdapter extends FragmentPagerAdapter {

	public TabsAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment;
		if(arg0 == 0)
			fragment = new OnePageFragment(); 
		else {
			fragment = new TwoPageFragment(); 
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 2;
	}

}
