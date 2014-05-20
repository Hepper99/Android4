package com.example.todolist;

import android.R.integer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class ToDoListItemView extends TextView {
	
	private Paint maginPaint;
	private Paint linePaint;
	private int paperColor;
	private float margin;
	
	public ToDoListItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ToDoListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ToDoListItemView(Context context) {
		super(context);
		init();
	}
	
	private void init()
	{
		Resources myResources = getResources();
		maginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		maginPaint.setColor(myResources.getColor(R.color.margin));
		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		linePaint.setColor(myResources.getColor(R.color.lines));
		paperColor = myResources.getColor(R.color.paper);
		margin = myResources.getDimension(R.dimen.margin);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(paperColor);
		canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);		
		canvas.drawLine(margin, 0, margin, getMeasuredHeight(), linePaint);
		canvas.save();
		canvas.translate(margin, 0);
		super.onDraw(canvas);
		canvas.restore();
	}
	
	
}
