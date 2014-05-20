package com.example.todolist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnNewItemAddedListener{

	private ArrayList<ToDoItem> toDoItems;
	private ArrayAdapter<ToDoItem> aa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FragmentManager fm = getFragmentManager();
        ToDoListFragment toDoListFragment = (ToDoListFragment)fm.findFragmentById(R.id.ToDoListFragment);
        toDoItems = new ArrayList<ToDoItem>();
        aa = new ArrayAdapter<ToDoItem>(this,R.layout.todolist_item,toDoItems);
        toDoListFragment.setListAdapter(aa);
        
        
    }

	@Override
	public void onNewItemAdded(String newItem) {
		ToDoItem toDoItem = new ToDoItem(newItem);
		toDoItems.add(0,toDoItem);
		aa.notifyDataSetChanged();
	}
}
