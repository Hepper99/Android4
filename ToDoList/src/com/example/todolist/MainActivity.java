package com.example.todolist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listView = (ListView)findViewById(R.id.lv);
        final EditText editView = (EditText)findViewById(R.id.et);
        final ArrayList<String> toDoItems = new ArrayList<String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,toDoItems);
        listView.setAdapter(aa);
        
        editView.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				System.out.println("ss");
				if(event.getAction() == KeyEvent.ACTION_DOWN)
					if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
							keyCode == KeyEvent.KEYCODE_ENTER){
						toDoItems.add(0,editView.getText().toString());
						aa.notifyDataSetChanged();
						return true;
					}
				return false;
			}
		});
    }
}
