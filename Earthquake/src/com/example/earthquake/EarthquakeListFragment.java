package com.example.earthquake;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.SumPathEffect;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.widget.ArrayAdapter;

public class EarthquakeListFragment extends ListFragment implements LoaderCallbacks<Cursor>{

	ArrayAdapter<Quake> aa;
	SimpleCursorAdapter adapter;
	ArrayList<Quake> earthQuakes = new ArrayList<Quake>();
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
				
		aa = new ArrayAdapter<Quake>(getActivity(), android.R.layout.simple_list_item_1,earthQuakes);

		adapter = new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,null,
											new String[] {EarthquakeProvider.KEY_SUMMARY},
											new int[] {android.R.id.text1},0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
		refreshEarthquakes();
	}	

	private void refreshEarthquakes() {	
		getLoaderManager().restartLoader(0, null, this);
		getActivity().startService(new Intent(getActivity(), EarthquakeUpdateService.class));
	}


	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
	    String[] projection = new String[] {
	    	      EarthquakeProvider.KEY_ID,
	    	      EarthquakeProvider.KEY_SUMMARY
	    	    }; 

	    	    MainActivity earthquakeActivity = (MainActivity)getActivity();
	    	    String where = EarthquakeProvider.KEY_MAGNITUDE + " > " + 
	    	                   earthquakeActivity.minimumMagnitude;
	    	   
	    	    CursorLoader loader = new CursorLoader(getActivity(), 
	    	      EarthquakeProvider.CONTENT_URI, projection, where, null, null);
	    	    
	    	    return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
		adapter.swapCursor(c);		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}
