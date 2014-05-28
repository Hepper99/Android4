package cn.zyy;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

	public PagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
//		NoteFragment[] a = NoteActivity.noteFragmentList.toArray(new NoteFragment[0]);
		return new NoteFragment(NoteActivity.currentDate - 100 + position);
	}

	@Override
	public int getCount() {
		return 200;
	}

}
