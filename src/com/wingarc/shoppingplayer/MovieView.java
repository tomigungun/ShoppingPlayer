package com.wingarc.shoppingplayer;

import java.util.ArrayList;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class MovieView extends VideoView implements Runnable {

	private boolean PAUSE = false;
	private boolean END = false;
	private ArrayList list = new ArrayList();
	private MyFrame frame;
	
    public MovieView(Context context) {
        super(context);
    }

    public MovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
	
    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }
	
    @Override
    public void pause() {
        super.pause();
    	PAUSE = true;
    }

    @Override
    public void start() {
        super.start();
    	
    	if(!PAUSE) {
		    Thread thread = new Thread(this);
		    thread.start();
    		END = false;
    	}
    	PAUSE = false;
    }

    public void ready() {
		
    	//‘æˆêˆø”‚Í•b”
    	//‘æ“ñˆø”‚Í1(show)A2(hide)
		
    	// scarf
    	ImageData data1 = new ImageData(frame.ID_IMAGE1, frame);
    	
    	data1.put(0, 1);
    	data1.put(7, 2);
    	data1.put(9, 1);
    	data1.put(13, 2);
    	data1.put(16, 1);
    	data1.put(26, 2);
    	
    	// pen
    	ImageData data2 = new ImageData(frame.ID_IMAGE2, frame);
    	
    	data2.put(4, 1);
    	data2.put(7, 2);
    	data2.put(10, 1);
    	data2.put(12, 2);
    	data2.put(16, 1);
    	data2.put(18, 2);
    	
    	list.add(data1);
    	list.add(data2);
    	
    	PAUSE = false;
    	END = false;
    }
	
    public void timerStop() {
    	END = true;
    }
	
	public void run(){
		
		int sec = 0;
		
		while(!END) {
			
			for(int i=0; i<list.size() ;i++) {
				ImageData data = (ImageData)list.get(i);
				data.check(sec);
			}
			
			if(!PAUSE) {
				sec++;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}