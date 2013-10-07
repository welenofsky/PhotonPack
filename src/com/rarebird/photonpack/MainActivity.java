package com.rarebird.photonpack;

import com.dancingsasquatch.photonsacks.R;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
//import android.view.Menu; Ditto, see below comment.
//import android.util.Log;
import android.view.MotionEvent;
//import android.widget.MediaController; We don't need controls for video
import android.widget.VideoView;

public class MainActivity extends Activity {
	VideoView vv;
	Uri uri;
	int startTime = 1111;
	int goodTimes = 1222;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Load video file on create
		uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pack_on);
		vv = (VideoView) findViewById(R.id.videoView1);
		// Need to try/catch this shit
		vv.setVideoURI(uri);
		//MediaController media_control = new MediaController(this);
	    //vv.setMediaController(media_control);
		vv.seekTo(startTime);
	    vv.setOnPreparedListener (new OnPreparedListener() {                    
	        @Override
	        public void onPrepared(MediaPlayer mp) {
	            mp.setLooping(true);
	        }
	    });
	}
	
	
/* Most Likely Do not need to override because I am not using a actionbar/menu 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this` adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int maskedAction = event.getActionMasked();
		int vidPos = vv.getCurrentPosition();
		if(vidPos < startTime | vidPos > 21000) vv.seekTo(startTime);
		
		Log.d("VideoPosition", String.valueOf(vidPos));
		switch (maskedAction) {
		case MotionEvent.ACTION_DOWN:
			if(!vv.isPlaying()) {
				vv.start();
			}
			vv.seekTo(goodTimes);
			break;
		case MotionEvent.ACTION_UP:
			// Stop Movie
		    vv.pause();
		    vv.seekTo(startTime);
			break;
		}
		return true;
	}
	@Override 
	protected void onResume(){
		super.onResume();
	    vv.requestFocus();
	    vv.seekTo(startTime);
	}
	public void playSounds() {
		// get oop son
	}

}
