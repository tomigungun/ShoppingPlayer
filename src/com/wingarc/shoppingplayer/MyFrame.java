package com.wingarc.shoppingplayer;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.wingarc.shoppingplayer.R;

/*
 * MyFrame
 */
public class MyFrame extends FrameLayout {
	// frame
	private static final int LAYOUT_FRAME = R.layout.commodity_frame;
	// image
	public static final int ID_IMAGE1 = R.id.Frame_ImageView1;
	public static final int ID_IMAGE2 = R.id.Frame_ImageView2;
	public static final int ID_IMAGE3 = R.id.Frame_ImageView3;
//	public static final int ID_IMAGE4 = R.id.Frame_ImageView4;
	
	// sensitivity
	private final static int SENSITIVITY = 5;
	// view component
	private View scarf;
	private View pen;
	private View shoppingCartOff;
	private View shoppingCartOn;

	// border
	private int mBorderRight = 0;
	private int mBorderBottom = 0;
	// flag for initialize
	private boolean isFirst = true;

	/**
	 * === constractor ====
	 */
	public MyFrame(Context context) {
		this(context, null);
	}

	/**
	 * === constractor ====
	 */
	public MyFrame(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * === constractor ====
	 */
	public MyFrame(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initFrame(context, attrs, defStyle);
	}

	/*
	 * initFrame
	 * 
	 * @param Context context
	 * 
	 * @param AttributeSet attrs
	 * 
	 * @param int defStyle
	 */
	private void initFrame(Context context, AttributeSet attrs, int defStyle) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(LAYOUT_FRAME, this, // we are the parent
				true);
		
		shoppingCartOff = (View) findViewById(ID_IMAGE3);
		shoppingCartOff.setTranslationX(0);
		shoppingCartOff.setTranslationY(180);
		shoppingCartOff.setScaleX(1.5F);
		shoppingCartOff.setScaleY(1.5F);
		
//		shoppingCartOn = (View) findViewById(ID_IMAGE4);
//		shoppingCartOn.setTranslationX(0);
//		shoppingCartOn.setTranslationY(130);
//		shoppingCartOn.setScaleX(0.8F);
//		shoppingCartOn.setScaleY(0.8F);
		
		scarf = (View) findViewById(ID_IMAGE1);
		scarf.setTranslationX(0);
		scarf.setTranslationY(0);
		scarf.setScaleX(1);
		scarf.setScaleY(1);
		scarf.setOnTouchListener(new ViewTouchListener(scarf));
//		scarf.setVisibility(View.INVISIBLE);
		
		pen = (View) findViewById(ID_IMAGE2);
		pen.setTranslationX(0);
		pen.setTranslationY(120);
		pen.setScaleX(1);
		pen.setScaleY(1);
		pen.setOnTouchListener(new ViewTouchListener(pen));
//		pen.setVisibility(View.INVISIBLE);
		
	}

	/*
	 * move view
	 * 
	 * @param int x
	 * 
	 * @param int y
	 */
	private void moveView(View view, int x, int y) {
		// initialize
		if (isFirst) {
			isFirst = false;
			mBorderRight = getWidth();
			mBorderBottom = getHeight();
		}
		// new position
		int left = view.getLeft() + x;
		int top = view.getTop() + y;
		redrawView(view, left, top);
	}

	/*
	 * redraw view
	 * 
	 * @param int left
	 * 
	 * @param int top
	 */
	private void redrawView(View view, int left, int top) {
		int right = left + scarf.getWidth();
		int bottom = top + scarf.getHeight();
		// border check
		if (left < 0)
			return;
		if (top < 0)
			return;
		if (right > mBorderRight)
			return;
		if (bottom > mBorderBottom)
			return;
		// redraw
		view.layout(left, top, right, bottom);
	}

	/*
	 * class ViewTouchListener
	 */
	private class ViewTouchListener implements OnTouchListener {
		// prevous position
		private int mPrevX = 0;
		private int mPrevY = 0;
		private View view;

		/*
		 * constractor
		 */
		public ViewTouchListener(View view) {
			this.view = view;
		}

		/*
		 * === onTouch ===
		 */
		@Override
		public boolean onTouch(View view, MotionEvent event) {
			// get position
			int x = (int) event.getRawX();
			int y = (int) event.getRawY();
			if ((Math.abs(mPrevX - x) < SENSITIVITY)
					&& (Math.abs(mPrevY - y) < SENSITIVITY)) {
				return true;
			}
			// action
			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				execMove(view, x, y);
				break;
			}
			// save position
			mPrevX = x;
			mPrevY = y;
			return true;
		}

		/*
		 * execute ACTION_MOVE
		 * 
		 * @param int x
		 * 
		 * @param int y
		 */
		private void execMove(View view, int x, int y) {
			// move difference
			int move_x = x - mPrevX;
			int move_y = y - mPrevY;
			moveView(view, move_x, move_y);
		}
	}

}