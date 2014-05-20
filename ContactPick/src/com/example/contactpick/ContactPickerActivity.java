package com.example.contactpick;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ContactPickerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		final Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		String[] from = new String[]{ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
		int[] to = new int[]{R.id.itv};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
										  R.layout.itemtextview,
										  c,
										  from,
										  to);
		ListView lv = (ListView)findViewById(R.id.lv);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos,
					long id) {
				c.moveToPosition(pos);
				int rowId = c.getInt(c.getColumnIndexOrThrow("_id"));
				Uri outUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);
				Intent outData = new Intent();
				outData.setData(outUri);
				setResult(Activity.RESULT_OK, outData);
				finish();
			}
			
		});
	}

}
