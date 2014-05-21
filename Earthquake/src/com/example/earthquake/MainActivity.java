package com.example.earthquake;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

public class MainActivity extends FragmentActivity {

	  public int minimumMagnitude = 0;
	  public boolean autoUpdateChecked = false;
	  public int updateFreq = 0;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    SearchManager searchManager = 
	        (SearchManager)getSystemService(Context.SEARCH_SERVICE);
	      SearchableInfo searchableInfo = 
	        searchManager.getSearchableInfo(getComponentName());

	      // Bind the Activity's SearchableInfo to the Search View
	      SearchView searchView = (SearchView)findViewById(R.id.searchView);
	      searchView.setSearchableInfo(searchableInfo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, Menu.FIRST+1, Menu.NONE, R.string.menu_preferences);
		return true;
	}
	private static final int SHOW_PREFERENCES = 1;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
	    switch (item.getItemId()) {
	      case (Menu.FIRST+1): {
	        Intent i = new Intent(this, PreferenceActivity.class);
	        startActivityForResult(i, SHOW_PREFERENCES);
	        return true;
	      }
	    }
	    return false;
	}
	
	private void updateFromPreferences() {
	    Context context = getApplicationContext();
	    SharedPreferences prefs = 
	      PreferenceManager.getDefaultSharedPreferences(context);

	    minimumMagnitude = prefs.getInt(PreferenceActivity.PREF_MIN_MAG_INDEX, 3);
	    updateFreq = prefs.getInt(PreferenceActivity.PREF_UPDATE_FREQ_INDEX, 60);

	    autoUpdateChecked = prefs.getBoolean(PreferenceActivity.PREF_AUTO_UPDATE, false);
	    String[] minMagValues = getResources().getStringArray(R.array.magnitude);
	    String[] freqValues = getResources().getStringArray(R.array.update_freq_values);
	    minimumMagnitude = Integer.valueOf(minMagValues[minimumMagnitude]);
	    updateFreq = Integer.valueOf(freqValues[updateFreq]);
	    
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if (requestCode == SHOW_PREFERENCES)
	      updateFromPreferences();
	      
//	      FragmentManager fm = getSupportFragmentManager();
//	      EarthquakeListFragment earthquakeList = 
//	          (EarthquakeListFragment)fm.findFragmentById(R.id.listfragment);
//	      earthquakeList.refreshEarthquakes();
	}
}
