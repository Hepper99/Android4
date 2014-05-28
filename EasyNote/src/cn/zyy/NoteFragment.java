package cn.zyy;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;

public class NoteFragment extends ListFragment implements LoaderCallbacks<Cursor>{
	
	private SimpleCursorAdapter adapter;
	private int date;
	NoteFragment(){}
	NoteFragment(int _date)
	{
		date = _date;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new SimpleCursorAdapter(getActivity(),R.layout.fragment_list,null,
											new String[] {NoteContentProvide.KEY_ITEMONE, NoteContentProvide.KEY_ITEMTWO},
											new int[] {R.id.text1, R.id.text2},0);
		setListAdapter(adapter);
		getLoaderManager().initLoader(0, null, this);
	}

	public void notifyDataChanged()
	{
		adapter.notifyDataSetChanged();
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
	    String[] projection = new String[] {
	    		NoteContentProvide.KEY_ID,
	    		NoteContentProvide.KEY_ITEMONE,
	    		NoteContentProvide.KEY_ITEMTWO,
	    		NoteContentProvide.KEY_DATE
	    	    }; 
	    String where = NoteContentProvide.KEY_DATE + " = " + date;
	    	   
	    CursorLoader loader = new CursorLoader(getActivity(), 
	    	    			NoteContentProvide.CONTENT_URI, projection, where, null, null);
	    	    
	    return loader;
	}
 
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
		adapter.swapCursor(c);			
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {		
	}
	
	
	

}
