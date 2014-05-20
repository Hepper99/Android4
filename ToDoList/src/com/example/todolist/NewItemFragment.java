package com.example.todolist;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NewItemFragment extends Fragment {

	private OnNewItemAddedListener onNewItemAddedListener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.new_item_fragment, container, false);

		
		final EditText editView = (EditText)view.findViewById(R.id.et);
		editView.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				System.out.println("ss");
				if(event.getAction() == KeyEvent.ACTION_DOWN)
					if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
							keyCode == KeyEvent.KEYCODE_ENTER){
						String newItem = editView.getText().toString();
						onNewItemAddedListener.onNewItemAdded(newItem);
						editView.setText("");
						return true;
					}
				return false;
			}
		});
		return view;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		onNewItemAddedListener = (OnNewItemAddedListener)activity;
	}
	
}
