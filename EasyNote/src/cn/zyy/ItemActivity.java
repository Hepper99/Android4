package cn.zyy;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ItemActivity extends Activity {

;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		TextView textView = (TextView)findViewById(R.id.ok);
		final EditText editText = (EditText)findViewById(R.id.itemone);
		final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
		
		textView.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				ContentResolver cr = getContentResolver();
				ContentValues contentValues = new ContentValues();
				  
				int date = NoteActivity.currentDate; 
				contentValues.put(NoteContentProvide.KEY_DATE, date);
				contentValues.put(NoteContentProvide.KEY_ITEMONE, editText.getText().toString());
				String yesOrNo; 
				if(R.id.yes == radioGroup.getCheckedRadioButtonId())
					yesOrNo = "ÊÇ";
				else
					yesOrNo = "·ñ";
				contentValues.put(NoteContentProvide.KEY_ITEMTWO, yesOrNo);
				cr.insert(NoteContentProvide.CONTENT_URI, contentValues);
				setResult(Activity.RESULT_OK);
				finish();
			}
		});
	}
	
}
