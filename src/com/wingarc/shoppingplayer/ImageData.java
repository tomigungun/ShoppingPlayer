package com.wingarc.shoppingplayer;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class ImageData {

	private int SECOND = 30;
	private int SHOW = 1;
	private int HIDE = 2;
	
	private int id;
	private int[] second = new int[SECOND];
	private MyFrame frame;
	
	private Handler handler1 = new Handler() {
	@Override
	public void handleMessage(Message msg) {
		ImageView imageView = (ImageView) frame.findViewById(id);
		imageView.setVisibility(View.VISIBLE);
	}
	};

	private Handler handler2 = new Handler() {
	@Override
	public void handleMessage(Message msg) {
		ImageView imageView = (ImageView) frame.findViewById(id);
		imageView.setVisibility(View.INVISIBLE);
	}
	};
	
    public ImageData(int id, MyFrame frame) {
 		this.id = id;
        this.frame = frame;
    	for(int i=0;i<SECOND;i++) {
    		second[i] = 0;
    	}
    	handler2.sendEmptyMessage(0);
    }
	
    public void put(int second, int action) {
		this.second[second] = action;
    }
	
    public void check(int sec) {
    	if(sec < SECOND) {
    		if(second[sec] == SHOW) {
    			
    			handler1.sendEmptyMessage(0);
//				ImageView imageView = (ImageView) frame.findViewById(id);
//				imageView.setVisibility(View.VISIBLE);
//    			imageView.invalidate();
    			
    		} else if(second[sec] == HIDE) {
    			
    			handler2.sendEmptyMessage(0);
//				ImageView imageView = (ImageView) frame.findViewById(id);
//    			imageView.setVisibility(View.INVISIBLE);
//    			imageView.invalidate();
    			
    		}
    	}
    }
}
