package com.lgs.Test1;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity1 extends Activity {
	private ImageView img_butterfly;
	private float currentX, currentY;// 当前位置的X,Y坐标
	private float nextX, nextY;// 下一位置的x,y坐标
	private Handler myHandler;
	private AnimationDrawable butterfly;
	private TranslateAnimation anim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		img_butterfly = (ImageView) findViewById(R.id.img_butterfly);
		myHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					if (currentX > 480) {
						currentX = nextX = 0;
					} else {
						nextX += 20;
					}
					nextY = currentY + (float) (Math.random() * 10 - 4);
					anim = new TranslateAnimation(currentX, nextX, currentY,
							nextY);
					currentX = nextX;
					currentY = nextY;
					anim.setDuration(500);
					img_butterfly.startAnimation(anim);
				}
			}
		};

		butterfly = (AnimationDrawable) img_butterfly.getBackground();
		img_butterfly.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				butterfly.start();
				new Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						myHandler.sendEmptyMessage(0);
					}
				}, 0, 500);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
